var currentSound
var soundURLs
var playerOn = false
var chimeSound
const soundURLsURL = "https://dl.dropbox.com/s/ze0stpqc6b0sj9p/trackURLs.json"
const chimeURL = "https://dl.dropbox.com/s/ex418k6cu0tmgoo/chime.mp3"


async function init() {
    soundURLs = await loadFile()

    playerOn = false

    setInterval(checkHour,1000)
}

async function loadFile() {
    var response = await fetch(soundURLsURL)
    var data = await response.json()
    return data
}
    
async function play() {
    volumeChange()
    if (playerOn) {
        pause();
    }
    playerOn = true
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
    playerOn = false
}

time = 1584986400;
function test() {

}

function volumeChange() {
    let newVolume = parseFloat(document.getElementById("volume").value)
    Howler.volume(newVolume)
}

var startedHourCountdown = false
function checkHour() {
    if (!playerOn) {
        return
    }
    let date = new Date();
    if (date.getMinutes() == 59) {
        console.log("59th minute")
        if (date.getSeconds() == 50) {
            console.log("began fading")
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
            console.log(msTillNextHour)
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
    if (!playerOn) {
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