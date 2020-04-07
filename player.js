var currentSound
var soundURLs
var state = "idle"
var chimeSound
const soundURLsURL = "https://dl.dropbox.com/s/ze0stpqc6b0sj9p/trackURLs.json"
const chimeURL = "https://dl.dropbox.com/s/ex418k6cu0tmgoo/chime.mp3"

browser.browserAction.onClicked.addListener(handlePress);

async function handlePress() {
    switch (state) {
        case "idle":
            await init()
            await play()
            break;
            
        case "paused":
            await play()
            break;

        case "playing":
            pause()
            break;
    }
}

function updateIcon(on) {
    if (on) {
        browser.browserAction.setIcon({
            path: {
                19: "icons/on-19.png",
                38: "icons/on-38.png"
            }
        })
    } else {
        browser.browserAction.setIcon({
            path: {
                19: "icons/off-19.png",
                38: "icons/off-38.png"
            }
        })
    }
}

async function init() {
    soundURLs = await loadFile()

    state = "paused"

    setInterval(checkHour,1000)
}

async function loadFile() {
    var response = await fetch(soundURLsURL)
    var data = await response.json()
    return data
}

async function play() {
    if (state == "playing") {
        pause();
    }
    state = "playing"
    hour = (new Date()).getHours()
    soundURLData = soundURLs[hour];
    intro = loadIntro(soundURLData.intro);
    main = loadMain(soundURLData.main);

    if (intro != null) {
        currentSound = intro;
        await playIntro(intro)
    }
    currentSound = main;
    main.play()

    updateIcon(true)
}

function loadIntro(introURL) {
    if (introURL != "") {
        return new Howl({
            src: [introURL],
            loop:false,
        })
    } else {
        return null;
    }
}

function loadMain(mainURL) {
    return new Howl({
        src: [mainURL],
        loop:true
    })
}

async function playIntro(intro) {
    return new Promise(resolve => {
        intro.play();
        intro.on('end', () => resolve())
    })
}

function pause() {
    currentSound.stop();
    state = "paused"

    updateIcon(false)
}

time = 1584986400;
function test() {

}

var startedHourCountdown = false
function checkHour() {
    if (state != "playing") {
        return
    }
    let date = new Date();
    if (date.getMinutes() == 59) {
        if (date.getSeconds() == 50) {
            currentSound.fade(1,0,9000)
        }
        
        if (!startedHourCountdown) {
            // let nextHourDate = new Date(
            //     date.getFullYear(),
            //     date.getMonth(),
            //     date.getDate(),
            //     date.getHours() + 1,
            //     0,
            //     0,
            //     0
            // )
            
            let nextHourDate = new Date(
                date.getFullYear(),
                date.getMonth(),
                date.getDate(),
                date.getHours() + 1,
                0,
                0,
                0
            )

            let msTillNextHour = nextHourDate.getTime() - date.getTime()
            startedHourCountdown = true
            setTimeout(switchHour,msTillNextHour)
            chimeSound = new Howl({ // loads the sound ready
                src: [chimeURL],
                loop: false,
                autoplay: false
            })
        }
    }
}

async function switchHour() {
    if (state != "playing") {
        return
    }
    
    pause()
    await playChime()
    play()

    startedHourCountdown = false
}

async function playChime() {
    return new Promise(resolve => {
        chimeSound.play();
        chimeSound.on('end',() => resolve())
    })
}

init()