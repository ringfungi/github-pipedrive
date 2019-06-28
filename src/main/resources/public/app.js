// get dom elements
const getGist = document.getElementById("getGist");
const updateDiv = document.getElementById("update");
const list = document.getElementById("list-ul");
const setKey = document.getElementById("setKey");
const lastToken = document.getElementById("lastToken");

// create array for json objects
let resJSON = [];
// used to save all users that has been scanned
const scannedUsers = [""];

// add event listener
getGist.addEventListener("click", () => {
  updateUI({});
  let userInput = document.getElementById("userName").value;
  let api_token = document.getElementById("apiKey").value;
  if (api_token === "") {
    document.getElementById("error").innerHTML = "api key can not be empty";
    setTimeout(() => {
      document.getElementById("error").innerHTML = " ";
    }, 3000);
    return null;
  }
  let data = gistData(userInput, api_token);
  data.then(function(value) {
    resJSON = value;
    value.forEach(item => {
      updateUI(item);
    });
  });

  // check if user has not already been added to the list of users scanned
  let check = scannedUsers.find(item => {
    return item === userInput;
  });

  if (check == undefined) { // means the user is not saved before
    setVisitedUser(userInput);
  }
});

// save used token for later use
setKey.addEventListener("click", () => {
  let api_token = document.getElementById("apiKey").value;
  saveUsedToke(api_token);
});

lastToken.addEventListener("click", () => {
  let token = useLastUsedToken();
  document.getElementById("apiKey").value = token;
});

//make a call to endpoint
async function gistData(userName, api_key) {
   const baseUri = "http://localhost:8080/newgists/";
 // const baseUri = 'http://35.234.159.163/newgists/';
  const res = await fetch(`${baseUri}${userName}/${api_key}`, {
    mode: "no-cors"
  });
  const resTex = await res.text(); // whatever is being fetched from the call,
                                   // it will convert it to a text. If there is data coming back it will be parsed to JSON.
  if (resTex) {
    return JSON.parse(resTex);
  } else {
    return [];
  }
}
// update ui
function updateUI(data) {
  let li = document.createElement("li");
  if (resJSON.length == 0) {
    li.innerHTML = "No gists found";
  } else {
    li.innerHTML = `Description: ${data.description}
                    <br>
                    Comments: ${data.comments}
                    <br>
                    Id: ${data.id}
                    `;
    list.appendChild(li);
  }
}

// save scanned users
function setVisitedUser(user) {
  scannedUsers.push(user);
  const storeUser = { all: scannedUsers, lastSeen: user };
  localStorage.setItem("userNames", JSON.stringify(storeUser));
}

// check for updates (single user)
function checkForUpdate() {
  let user = JSON.parse(localStorage.getItem("userNames"));
  let data = gistData(user.lastSeen, useLastUsedToken());
  data.then(function(value) {
    if (value.length > resJSON.length) {
      updateDiv.innerHTML = `${user.lastSeen} has created a new item`;
    } else {
      updateDiv.innerHTML = `no updates `;
    }
  });
}

// check for update every 3 hours
let updateTime = 3000 * 60 * 60;
setInterval(() => {
  console.log("checking for stuff..");
  checkForUpdate();
}, updateTime);

function saveUsedToke(token) {
  localStorage.setItem("lastToken", token);
}

function useLastUsedToken() {
  let token = localStorage.getItem("lastToken");
  if (token) {
    return token;
  } else {
    return "Found nothing";
  }
}
