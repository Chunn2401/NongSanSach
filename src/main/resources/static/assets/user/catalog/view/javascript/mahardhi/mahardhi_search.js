$(document).ready(function() {
	$(".mahardhi-search input[name='search']").autocomplete({
		source: function(request, response) {
			const categoryId = ($('#mahardhiSearch select[name=\'category_id\']').val()) ? Number($('#mahardhiSearch select[name=\'category_id\']').val()) : 0;
			const url = "index.php?route=extension/module/mahardhi_search&filter_name=" + request.term + "&filter_category=" + categoryId;
			$.ajax({
				url,
				data: {
					keyword: request.term
				},
				dataType: "json",
				success: function(json) {
					response($.map(json, function(item) {
						if (item.name) {
							return {
								name: item.name,
								image: item.image,
								price: item.price,
								special: item.special,
								value: item.href
							}
						}
					}));
				}
			})
		},
		minLength: 1,
		select: function(event, ui) {
			if (ui.item.value == "") {
				return false;
			} else {
				var link = (ui.item.value).replace(/&amp;/g, '&');
				location.href = link;
				return false;
			}
		},
		open: function() {
			$(this).removeClass("ui-corner-all").addClass("ui-corner-top");
		},
		close: function() {
			$(this).removeClass("ui-corner-top").addClass("ui-corner-all");
		},
		focus: function(event, ui) {
			var name = (ui.item.name).replace(/&amp;/g, "&").replace(/&gt;/g, ">").replace(/&lt;/g, "<").replace(/&quot;/g, '"');
			$('#search input[name="search"]').val(name);
			return false;
		}
	});

	$.ui.autocomplete.prototype._renderItem = function(ul, item) {
		var elements = [];
		var field_html1 = '<div class="images"><img class="product-image img-responsive" src="' + item.image + '" /></div>';
		elements.push({html: field_html1});

		var field_html2 = '<div class="product-detail"><span class="product-name">' + item.name + '</span>';
		elements.push({html: field_html2});

		//  = '<span class="produc-price">' + item.price + '</span>';
		var field_html3 =''
		if (item.special) {
			field_html3 = '<div class="price"><span class="price-new">' + item.special + '</span><span class="price-old">' + item.price + '</span></div></div>';
		} else {
			field_html3='<div class="price"><span class="price-new">' + item.price + '</span></div></div>';
		}
		elements.push({html: field_html3});
		// implode
		var elements_html = '';
		$.each(elements, function(index, element) {
			if (element != undefined) {
				elements_html = elements_html + element.html;
			}
		});
		return $("<li></li>")
			.data("ui-autocomplete-item", item)
			.append('<a href="'+ item.value +'" class="mahardhi-search clearfix">' + elements_html + '</a>')
			.appendTo(ul);
	};
});
