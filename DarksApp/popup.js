let element = document.getElementById("button");
element.addEventListener('click', function () {

    chrome.tabs.query({}, function (tabs) {
        console.log("\n/////////////////////\n");
        tabs.forEach(function (tab) {
            if (tab.url === "https://web.whatsapp.com/") {
                console.log(tab.url, " ", tab.id);
                chrome.tabs.executeScript(tab.id, {
                    code: 'document.getElementsByTagName("body")[0].classList.toggle("dark");'
                });
            }

        });
    });

    if (element.innerHTML === "Light")
        element.innerHTML = "Dark";
    else
        element.innerHTML = "Light";
});