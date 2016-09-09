$( "#date" ).datepicker({
			  onSelect: function(dateText) {
				var date = $(this).datepicker('getDate');
				var day = document.getElementById("day");
				var dateBox = document.getElementById("dateBox");
				dateBox.value = $.datepicker.formatDate('dd-mm-yy', date);
				day.innerHTML = $.datepicker.formatDate('DD dd', date);
				$("#day_calendar").fadeIn("slow");
			  }
			});