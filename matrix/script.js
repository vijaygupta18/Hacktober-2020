   
        let res = document.querySelector('div#result')
        let divs = document.querySelectorAll('div.elements')
        let btn = document.querySelector('button')
        let btnZera = document.getElementById('zera')
        let linha = document.querySelector('div.linhas')

        let enter = document.addEventListener('keypress', (e) => {
            if (e.keyCode == 13) {
                addMatrix()
            }
        })

        btn.addEventListener('click', addMatrix)
        btnZera.addEventListener('click', zeraMatrix)
 

    function addMatrix() {

            res.classList.remove('hide')
            res.innerHTML=""

         let m = []
    
        
             divs.forEach(div => {
                 m.push(Number(div.textContent))
                 div.style.backgroundColor = `${getRandomColor()}`
             })
            
            
            prim = m[0]+ m[4]+ m[8]
            
            sec = m[2] + m[4] + m[6]

            total = m.reduce((total,next) => total + next)

             
      res.innerHTML += `<p>Soma da diagonal primária: ${prim}</p>` 
      res.innerHTML += `<p>Soma da diagonal secundária: ${sec}</p>` 
      res.innerHTML += `<p>Soma de todos elementos: ${total}</p>`      

        
    }

    function zeraMatrix(){
        
        divs.forEach(div=>{
            div.textContent = ""
            div.style.backgroundColor = 'aliceblue'
        })

        res.classList.add('hide')
        
    }

    function getRandomColor() {
        var letters = "0123456789ABCDEF";
        var color = "#";
        for (var i = 0; i < 6; i++) {
             color += letters[Math.floor(Math.random() * 16)];
        }
        return color;
}