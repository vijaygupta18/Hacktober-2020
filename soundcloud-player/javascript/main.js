//Search
//query SoundCloud api

var SoundCloudAPI={};
SoundCloudAPI.init=function() {
        SC.initialize({
                client_id: 'cd9be64eeb32d1741c17cb39e41d254d'
        });
}
SoundCloudAPI.init();
SoundCloudAPI.getTrack=function(inputValue) {
        SC.get('/tracks', {
                q: inputValue
        }).then(function(tracks) {
                console.log(tracks);
                SoundCloudAPI.renderTracks(tracks);
        });
}
var UI = {};


UI.handleEnterPress = function() {
	document.querySelector(".js-search").addEventListener('keypress', function( e ) {
			var inputValue = e.target.value;
			SoundCloudAPI.getTrack(inputValue);
	});
}

UI.handleSubmitClick = function() {
	document.querySelector(".js-submit").addEventListener('click', function( e ) {
		var inputValue = document.querySelector(".js-search").value;
		//onValueRead( inputValue );
		//console.log(inputValue);
		SoundCloudAPI.getTrack(inputValue);
	});
}

// // set up the search
UI.handleEnterPress();
UI.handleSubmitClick();


//display the cards
SoundCloudAPI.renderTracks = function(tracks) {
        var container = document.querySelector(".search-results");
        container.innerHTML=null;
        tracks.forEach(function(track){
                var card =document.createElement('div');
                card.classList.add('card');

                var imageDiv =document.createElement('div');
                imageDiv.classList.add('image');

                var image_img=document.createElement('img');
                image_img.classList.add('image_img');
                image_img.src=track.artwork_url || 'https://via.placeholder.com/150';
                imageDiv.appendChild(image_img);

                var content=document.createElement('div');
                content.classList.add('content');

                var header=document.createElement('div');
                header.classList.add('header');
                header.innerHTML='<a href="'+track.permalink_url+'" target=blank>'+track.title+'</a>';

                var button=document.createElement('div');
                button.classList.add('ui','bottom','attached','button','js-button');

                var icon=document.createElement('i');
                icon.classList.add('add','icon');

                var buttonText=document.createElement('span');
                buttonText.innerHTML='Add to playlist';

                content.appendChild(header);
                button.appendChild(icon);
                button.appendChild(buttonText);
                button.addEventListener('click',function(){
                        SoundCloudAPI.getEmbed(track.permalink_url);
                });
                card.appendChild(imageDiv);
                card.appendChild(content);
                card.appendChild(button);
                var searchResults=document.querySelector(".js-search-results");
                searchResults.appendChild(card);
        });


}
SoundCloudAPI.getEmbed=function(trackURL){
        SC.oEmbed(trackURL, {
                auto_play: true
        }).then(function(embed){
                console.log('oEmbed response: ', embed);
                var sideBar=document.querySelector('.js-playlist');
                var box=document.createElement('div');
                box.innerHTML=embed.html;
                sideBar.insertBefore(box,sideBar.firstChild);
                localStorage.setItem("key",sideBar.innerHTML);
        });
};

var sideBar=document.querySelector('.js-playlist');
sideBar.innerHTML=localStorage.getItem("key");
