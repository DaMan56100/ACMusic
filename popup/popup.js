document.querySelector("#playButton").addEventListener("click", () => {
    browser.runtime.sendMessage({
        title: "volumeChange",
        newVolume: getVolume()
    })
    browser.runtime.sendMessage({
        title: "play"
    })
})

document.querySelector("#pauseButton").addEventListener("click", () => {
    browser.runtime.sendMessage({
        title: "pause"
    })
})

document.querySelector("#volumeSlider").addEventListener("input", () => {
    browser.runtime.sendMessage({
        title: "volumeChange",
        newVolume: getVolume()
    })
})

document.querySelector("#volumeSlider").addEventListener("change", async () => {
    let newVolume = getVolume();
    let volumeCookie = await browser.cookies.set({
        url: "https://daman56100.github.io/ACMusic/",
        name: "AC Volume",
        value: newVolume
    })
})

window.onload = async () => {
    let volumeCookie = await browser.cookies.get({
        name: "AC Volume",
        url: "https://daman56100.github.io/ACMusic/"
    })
    document.querySelector("#volumeSlider").value = volumeCookie.value
}

function getVolume() {
    return document.querySelector("#volumeSlider").value
}
//