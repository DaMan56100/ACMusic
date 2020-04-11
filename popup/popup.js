document.querySelector("#playButton").addEventListener("click", () => {
    chrome.runtime.sendMessage({
        title: "volumeChange",
        newVolume: getVolume()
    })
    chrome.runtime.sendMessage({
        title: "play"
    })
})

document.querySelector("#pauseButton").addEventListener("click", () => {
    chrome.runtime.sendMessage({
        title: "pause"
    })
})

document.querySelector("#volumeSlider").addEventListener("input", () => {
    chrome.runtime.sendMessage({
        title: "volumeChange",
        newVolume: getVolume()
    })
})

document.querySelector("#volumeSlider").addEventListener("change", async () => {
    let newVolume = getVolume();
    chrome.storage.sync.set({volume:newVolume}, () => {})
})

window.onload = async () => {
    chrome.storage.sync.get(['volume'], (respone) => {
        document.querySelector("#volumeSlider").value = respone.volume
        console.log(respone.volume)
    })
}

function getVolume() {
    return document.querySelector("#volumeSlider").value
}
//