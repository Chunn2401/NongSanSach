
$('.date').datetimepicker({
	language: 'en-gb',
	pickTime: false
});

$('.datetime').datetimepicker({
	language: 'en-gb',
	pickDate: true,
	pickTime: true
});

$('.time').datetimepicker({
	language: 'en-gb',
	pickDate: false
});

$('button[id^=\'button-upload\']').on('click', function () {
	var node = this;

	$('#form-upload').remove();

	$('body').prepend('<form enctype="multipart/form-data" id="form-upload" style="display: none;"><input type="file" name="file" /></form>');

	$('#form-upload input[name=\'file\']').trigger('click');

	if (typeof timer != 'undefined') {
		clearInterval(timer);
	}

	timer = setInterval(function () {
		if ($('#form-upload input[name=\'file\']').val() != '') {
			clearInterval(timer);

			$.ajax({
				url: 'index.php?route=tool/upload',
				type: 'post',
				dataType: 'json',
				data: new FormData($('#form-upload')[0]),
				cache: false,
				contentType: false,
				processData: false,
				beforeSend: function () {
					$(node).button('loading');
				},
				complete: function () {
					$(node).button('reset');
				},
				success: function (json) {
					$('.text-danger').remove();

					if (json['error']) {
						$(node).parent().find('input').after('<div class="text-danger">' + json['error'] + '</div>');
					}

					if (json['success']) {
						alert(json['success']);

						$(node).parent().find('input').val(json['code']);
					}
				},
				error: function (xhr, ajaxOptions, thrownError) {
					alert(thrownError + "\r\n" + xhr.statusText + "\r\n" + xhr.responseText);
				}
			});
		}
	}, 500);
});


// Additional images
const direction = $('html').attr('dir');

$('#additional-carousel').each(function () {
	if ($(this).closest('#column-left').length == 0 && $(this).closest('#column-right').length == 0) {
		$(this).addClass('owl-carousel owl-theme');
		const items = $(this).data('items') || 4;
		const sliderOptions = {
			loop: false,
			nav: true,
			navText: ['<i class="fa fa-caret-left"></i>', '<i class="fa fa-caret-right"></i>'],
			dots: false,
			items: items,
			mouseDrag: false,
			touchDrag: false,
			pullDrag: false,
			rewind: false,
			autoplay: true,
			responsiveRefreshRate: 200,
			responsive: {
				0: { items: 1 },
				320: { items: ((items - 2) > 1) ? (items - 2) : 1 },
				376: { items: ((items - 1) > 1) ? (items - 1) : 1 },
				1341: { items: items }
			}
		};
		if (direction == 'rtl') sliderOptions['rtl'] = true;
		$(this).owlCarousel(sliderOptions);
	}
});

// related images
const dirrelated = $('html').attr('dir');
$('.related-carousel').each(function () {
	if ($(this).closest('#column-left').length == 0 && $(this).closest('#column-right').length == 0) {
		$(this).addClass('owl-carousel owl-theme');
		const items = $(this).data('items') || 5;
		const sliderOptions = {
			loop: false,
			rewind: false,
			autoplay: false,
			autoplayTimeout: 3000,
			nav: true,
			mouseDrag: true,
			touchDrag: true,
			navText: ['<i class="fa fa-caret-left"></i>', '<i class="fa fa-caret-right"></i>'],
			dots: false,
			items: items,
			responsiveRefreshRate: 200,
			responsive: {
				0: { items: 1 },
				320: { items: ((items - 3) > 1) ? (items - 3) : 1 },
				541: { items: ((items - 2) > 1) ? (items - 2) : 1 },
				1200: { items: ((items - 1) > 1) ? (items - 1) : 1 },
				1341: { items: items }
			}
		};
		if (dirrelated == 'rtl') sliderOptions['rtl'] = true;
		$(this).owlCarousel(sliderOptions);
	}
});

$(document).ready(function () {
	if ($(window).width() > 991) {
		$("#zoom").elevateZoom({

		});

		var image_index = 0;
		$(document).on('click', '.thumbnail', function () {
			$('.thumbnails').magnificPopup('open', image_index);
			return false;
		});

		$('#additional-carousel a').click(function () {
			var smallImage = $(this).attr('data-image');
			var largeImage = $(this).attr('data-zoom-image');
			var ez = $('#zoom').data('elevateZoom');
			$('.thumbnail').attr('href', largeImage);
			ez.swaptheimage(smallImage, largeImage);
			image_index = $(this).index('#additional-carousel a');
			return false;
		});

	} else {
		$(document).on('click', '.thumbnail', function () {
			$('.thumbnails').magnificPopup('open', 0);
			return false;
		});
	}
});

$(document).ready(function () {
	$('.thumbnails').magnificPopup({
		type: 'image',
		delegate: 'a.elevatezoom-gallery', // Mahardhi Edit
		gallery: {
			enabled: true
		}
	});
});

