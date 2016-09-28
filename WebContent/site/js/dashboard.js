/* To control scope of module specific methods */
var dashboardService = function() {
	this.updateProductList = function(data) {
		var prodList = $("ul.product_list");
		var rowTemplate = $("ul.product_list li");
		for(var i=0; i<data.length; ++i) {
			var rowTemplateCopy = rowTemplate.clone();
			$(".title_area .textcontent", rowTemplateCopy).html(data[i].productNickName);
			rowTemplateCopy.appendTo(prodList);
		}
		$("ul.product_list li:first").remove();
	};
};

$(function() {
	var dashboard = new dashboardService();
	$.ajax({url: "../product/list", success: function(data) {
		dashboard.updateProductList(data);
	}});
});