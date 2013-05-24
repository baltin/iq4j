function checkAll(e) {
	
	var checked = e.checked;
	var findBox = $(e).parents('.cmp_smc_gr').find('.ui-chkbox-box');
	var findIcon = $(e).parents('.cmp_smc_gr').find('.ui-chkbox-icon');
	var checkbox = $(e).parents('.cmp_smc_gr').find('input[type=checkbox]');
	
	if (!checked) {
		checkbox.removeAttr('checked');
		findBox.removeClass('ui-state-active');
		findIcon.removeClass('ui-icon ui-icon-check');
	} else {
		checkbox.attr('checked', 'checked');
		findBox.addClass('ui-state-active');
		findIcon.addClass('ui-icon ui-icon-check');
	}
	
}

function checkItem(e) {

	var checked = e.checked;
	var checkbox = $(e).parents('.cmp_blck').find('input[type=checkbox]');
	var box = $(e).parents('.cmp_smc_gr').find('.cmp_smc_ca .ui-chkbox-box');
	var icon = $(e).parents('.cmp_smc_gr').find('.cmp_smc_ca .ui-chkbox-icon');
	var upperBox = $(e).parents('.cmp_smc_gr').find('.cmp_smc_ca div input[type=checkbox]');
	var checkList = [];
	
	if(!checked) {
		upperBox.removeAttr('checked');
		box.removeClass('ui-state-active');
		icon.removeClass('ui-icon ui-icon-check');
	}
	
	$(checkbox).each(function() {
		if(!this.checked) {
			checkList.push(this);
		}
	})
	
	if(checkList.length == 0) {
		upperBox.attr('checked', 'checked');
		box.addClass('ui-state-active');
		icon.addClass('ui-icon ui-icon-check');
	}
	
	
}