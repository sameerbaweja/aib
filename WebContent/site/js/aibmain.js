var mask = function(ele, text){
	if(!text){
		text = "Loading...";
	}
	var maskDiv = $(document.createElement('div'));
	maskDiv.addClass("masking");
	var maskingContent = "<div class='page-loading'><img style = 'margin-top: 20%;' src = ../images/Loading-blue.gif /><label style = 'letter-spacing: 0.9px; vertical-align: bottom;'>"+text+"</label></div>";
	maskDiv.html(maskingContent);
	$(ele).append(maskDiv);
};

var unMask = function(ele){
	if($(ele).find('.masking').is('div')){
		$(ele).find('.masking').remove();
	}
};
