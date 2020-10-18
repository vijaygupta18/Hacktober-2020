class CountryList extends HTMLElement {

    constructor() {
        super();
        this.shadowDOM = this.attachShadow({ mode: "open" });
    }

    set country(country) {
        this._country = country;
        this.render();
    }

    render() {
        this.shadowDOM.innerHTML = "";
        const countryItemElement = `
        <style>
            :host {
                display: block;
                margin-bottom: 18px;
                box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
                border-radius: 10px;
                overflow: hidden;
            }
            
            .card-info {
                padding: 24px;
            }
            .card {
                position: relative;
                display: -ms-flexbox;
                display: flex;
                -ms-flex-direction: column;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 1px solid rgba(0,0,0,.125);
                border-radius: .25rem;
            }
            .text-center {
                text-align: center!important;
            }
            .p-3 {
                padding: 1rem!important;
            }
            .card-body {
                -ms-flex: 1 1 auto;
                flex: 1 1 auto;
                min-height: 1px;
                padding: 1.25rem;
            }
            @media (min-width: 992px) {
                .col-lg-4 {
                    -ms-flex: 0 0 33.333333%;
                    flex: 0 0 33.333333%;
                    max-width: 33.333333%;
                } 
            }
            @media (min-width: 576px) {
                .col-sm-4 {
                    -ms-flex: 0 0 33.333333%;
                    flex: 0 0 33.333333%;
                    max-width: 33.333333%;
                }
            }
            @media screen and (min-width: 350px){
                .col-sm-4 {
                    -ms-flex: 0 0 33.333333%;
                    flex: 0 0 33.333333%;
                    max-width: 33.333333%;
                }
            }
            .row {
                display: -ms-flexbox;
                display: flex;
                -ms-flex-wrap: wrap;
                flex-wrap: wrap;
                margin-right: -15px;
                margin-left: -15px;
            }
            .bg-green {
                background-color: #00a65a !important;
            }
            .blue {
                color: #00c0ef !important;
            }
            .green {
                color: #00a65a !important;
            }
            .red {
                color: #d9534f !important;
            }
            .h1 {
                line-height: 1.2rem;
                font-size: 27px;
                font-weight: bold;
                margin: 0 0 8px 0;
                white-space: nowrap;
            }
        </style>
        
        <div class="row">
            <div class="col-sm-4 col-lg-4">
                <div class="card">
                    <div class="card-body p-3 text-center">
                        <div id="provinsiConfirmed" class="h1 m-0">${this._country.confirmed.value}</div>
                        <div class="mb-3 blue">Confirmed</div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4 col-lg-4">
                <div class="card">
                    <div class="card-body p-3 text-center">
                        <div id="provinsiRecovered" class="h1 m-0">${this._country.recovered.value}</div>
                        <div class="mb-3 green">Recovered</div>
                    </div>
                </div>
            </div>
            <div class="col-sm-4 col-lg-4">
                <div class="card">
                    <div class="card-body p-3 text-center">
                        <div id="provinsiDeath" class="h1 m-0">${this._country.deaths.value}</div>
                        <div class="mb-3 red">Death</div>
                    </div>
                </div>
            </div>
        </div>`;
        this.shadowDOM.innerHTML = countryItemElement;
    }

    renderError(message) {
        this.shadowDOM.innerHTML = `
        <style>
             .placeholder {
                   font-weight: lighter;
                   color: rgba(0,0,0,0.5);
                   -webkit-user-select: none;
                   -moz-user-select: none;
                   -ms-user-select: none;
                   user-select: none;
                   text-align: center;
                   color: red;
               }
        </style>`;
        this.shadowDOM.innerHTML += `<h2 class="placeholder">${message}</h2>`;
    }
}

customElements.define("country-list", CountryList);