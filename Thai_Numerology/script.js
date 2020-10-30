const days = {
    อาทิตย์: 1,
    จันทร์: 2,
    อังคาร: 3,
    พุธ: 4,
    พฤหัสบดี: 5,
    ศุกร์: 6,
    เสาร์: 7
};

const months = {
    มกราคม: 2,
    กุมภาพันธ์: 3,
    มีนาคม: 4,
    เมษายน: 5,
    พฤษภาคม: 6,
    มิถุนายน: 7,
    กรกฎาคม: 1,
    สิงหาคม: 2,
    กันยายน: 3,
    ตุลาคม: 4,
    พฤศจิกายน: 5,
    ธันวาคม: 1
};

const thaiYears = {
    ชวด: 1,
    ฉลู: 2,
    ขาล: 3,
    เถาะ: 4,
    มะโรง: 5,
    มะเส็ง: 6,
    มะเมีย: 7,
    มะแม: 1,
    วอก: 2,
    ระกา: 3,
    จอ: 4,
    กุน: 5
};


function base123(dayCode) {
    let result = []
    let counter = 0
    while (counter < 7) {
        if (dayCode > 7)
            dayCode = 1

        result.push(dayCode)
        dayCode++
        counter++
    }

    return result
}

function base4th(baseD, baseM, baseY) {
    result = []
    for (let i = 0; i < baseD.length; i++) {
        _sum = baseD[i] + baseM[i] + baseY[i]
        result.push(_sum)
    }

    return result
}

function base5th(base4th) {
    result = []
    for (let num of base4th) {
        if (num > 7) {
            num -= 7
            if (num > 7)
                num -= 7
        }
        result.push(num)
    }

    return result
}

function base67(preBase) {
    result = []
    for (let num of preBase) {
        num *= 2
        if (num > 7) {
            num -= 7
            if (num > 7) {
                num -= 7
            }
        }

        result.push(num)
    }

    return result
}

function base8th(base5) {
    template = [1, 6, 4, 2, 7, 5, 3]
    len = 7
    result = []
    firstB5 = base5[0]
    idx = template.indexOf(firstB5)
    counter = 0
    while (counter < 7) {
        if (idx >= len) {
            idx = 0
        }
        result.push(template[idx])
        idx++
        counter++
    }

    return result
}

function base9th(base5, base8) {
    template = [3, 5, 7, 2, 4, 6, 1]
    len = 7
    result = []
    lastNum = base5[6] + base8[6]
    if (lastNum > 7) lastNum -= 7

    idx = template.indexOf(lastNum) + 1
    counter = 0

    while (counter < 7) {
        if (idx >= 7) {
            idx = 0
        }
        result.push(template[idx])
        idx++
        counter++
    }

    return result
}

$(document).ready(function () {
    $('#display-zone').collapse('hide')

    $('#fortune-btn').on('click', function () {
        let theDay = $('#the-day option:selected').text()
        let day = $('#day option:selected').text()
        let month = $('#month option:selected').text()
        let year = $('#year option:selected').text()

        $('#display-the-day').text(theDay)
        $('#display-day').text(day)
        $('#display-month').text(month)
        $('#display-year').text(year)
        $('#display-zone').collapse('show')

        const base1 = base123(days[theDay])
        const base2 = base123(months[month])
        const base3 = base123(thaiYears[year])
        const base4 = base4th(base1, base2, base3)
        const base5 = base5th(base4)
        const base6 = base67(base5)
        const base7 = base67(base6)
        const base8 = base8th(base5)
        const base9 = base9th(base5, base8)
        const table = [base1, base2, base3, base4, base5, base6, base7, base8, base9]

        for (let i = 0; i < 9; i++) {
            for (let j = 0; j < 7; j++) {
                id = `#${i}${j}`
                $(id).text(table[i][j])
            }
        }
    })
})