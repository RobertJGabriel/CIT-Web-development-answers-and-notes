 function resize()
            {
                var heights = window.innerHeight;
				        var widths = window.innerWidth;
						
						
					 
		
                document.getElementById("left").style.height = heights  + "px";
			        document.getElementById("right").style.height = heights  + "px";
						
			        document.getElementById("right").style.width = widths - 250 + "px";
			
            }
          