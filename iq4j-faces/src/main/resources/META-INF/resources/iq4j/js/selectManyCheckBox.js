// CheckAll function is for selectManyCheckBox composite.
function checkAll(e) {
	var checked = e.checked;
	if (!checked) {
		$(e).parents('.cmp_smc_gr').find('input[type=checkbox]').removeAttr('checked');
		$(e).parents('.cmp_smc_gr').find('.ui-chkbox-box').removeClass('ui-state-active');
		$(e).parents('.cmp_smc_gr').find('.ui-chkbox-icon').removeClass('ui-icon ui-icon-check');
	} else {
		$(e).parents('.cmp_smc_gr').find('input[type=checkbox]').attr('checked', 'checked');
		$(e).parents('.cmp_smc_gr').find('.ui-chkbox-box').addClass('ui-state-active');
		$(e).parents('.cmp_smc_gr').find('.ui-chkbox-icon').addClass('ui-icon ui-icon-check');
	}
}