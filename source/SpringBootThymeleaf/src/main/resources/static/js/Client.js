(function(a)
		{a(document).ready(function()
				{var b=a(".chatbox"),
			c=a(".chatbox__title"),
			d=a(".chatbox__title__close");
				a(".chatbox__credentials");
				c.on("click",function()
						{b.toggleClass("chatbox--tray")});
				d.on("click",function(a)
						{a.stopPropagation();
						b.addClass("chatbox--closed")});
				b.on("transitionend",function(){b.hasClass("chatbox--closed")&&b.remove()})})})(jQuery);