$(document).ready(function() {
	
	ajaxGet();
	
	// DO GET
	function ajaxGet(){

		$.ajax({
			type : "GET",
			url : window.location + "largewaves",
			success: function(result){
                $("#tsunamiTable").find("tr:gt(0)").remove();
				$.each(result, function(i, tsunami){
					
					var tsunamiRow = '<tr>' +
										'<td>' + tsunami.country + '</td>' +
										'<td>' + tsunami.year + '</td>' +
										'<td>' + tsunami.height + '</td>' +
									  '</tr>';
					
					$('#tsunamiTable tbody').append(tsunamiRow);
					
		        });
				
				$( "#tsunamiTable tbody tr:odd" ).addClass("info");
				$( "#tsunamiTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
        setInterval(ajaxGet, 3000);
    }
})