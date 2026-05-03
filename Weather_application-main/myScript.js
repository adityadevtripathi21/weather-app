document.addEventListener("DOMContentLoaded", function () {

    const weatherIcon = document.getElementById("weather-icon");
    const wcElement = document.getElementById("wc");

    // ✅ SAFE CHECK
    if (weatherIcon && wcElement) {

        const val = wcElement.value;

        switch (val) {

            case 'Clouds':
                weatherIcon.src = "https://cdn-icons-png.flaticon.com/512/414/414825.png";
                break;

            case 'Clear':
                weatherIcon.src = "https://cdn-icons-png.flaticon.com/512/869/869869.png";
                break;

            case 'Rain':
                weatherIcon.src = "https://cdn-icons-png.flaticon.com/512/1163/1163624.png";
                break;

            case 'Drizzle':
                weatherIcon.src = "https://cdn-icons-png.flaticon.com/512/3076/3076129.png";
                break;

            case 'Thunderstorm':
                weatherIcon.src = "https://cdn-icons-png.flaticon.com/512/1146/1146860.png";
                break;

            case 'Snow':
                weatherIcon.src = "https://cdn-icons-png.flaticon.com/512/642/642102.png";
                break;

            case 'Mist':
            case 'Fog':
                weatherIcon.src = "https://cdn-icons-png.flaticon.com/512/4005/4005901.png";
                break;

            case 'Haze':
                weatherIcon.src = "https://cdn-icons-png.flaticon.com/512/1197/1197102.png";
                break;

            default:
                // ✅ fallback icon
                weatherIcon.src = "https://cdn-icons-png.flaticon.com/512/1163/1163661.png";
        }
    }

    // ✅ TIME FUNCTION
    function updateTime() {
        const currentTime = new Date();

        let hours = currentTime.getHours();
        let minutes = currentTime.getMinutes();

        if (minutes < 10) {
            minutes = "0" + minutes;
        }

        let period = hours >= 12 ? "PM" : "AM";

        // optional 12-hour format
        if (hours > 12) {
            hours = hours - 12;
        }
        if (hours === 0) {
            hours = 12;
        }

        const timeStr = `${hours}:${minutes} ${period}`;

        const timeElement = document.getElementById("time_span");

        if (timeElement) {
            timeElement.innerHTML = timeStr;
        }
    }

    updateTime(); // run once immediately
    setInterval(updateTime, 1000);
});