function checkAll(e) {
	
	var checked = e.checked;
	var smcgr = $(e).parents(".cmp_smc_gr");
	var box = smcgr.find(".ui-chkbox-box");
	var icon = smcgr.find(".ui-chkbox-icon");
	var checkbox = smcgr.find("input[type=checkbox]");
	
	if (!checked) {
		checkbox.removeAttr("checked");
		box.removeClass("ui-state-active");
		icon.removeClass("ui-icon ui-icon-check");
	} else {
		checkbox.attr("checked", "checked");
		box.addClass("ui-state-active");
		icon.addClass("ui-icon ui-icon-check");
	}
	
}

function checkItem(e) {

	var checked = e.checked;
	var checkbox = $(e).parents(".cmp_blck").find("input[type=checkbox]");
	var smcgr = $(e).parents(".cmp_smc_gr");
	var box = smcgr.find(".cmp_smc_ca .ui-chkbox-box");
	var icon = smcgr.find(".cmp_smc_ca .ui-chkbox-icon");
	var upperBox = smcgr.find(".cmp_smc_ca div input[type=checkbox]");
	var checkList = [];
	
	if(!checked) {
		upperBox.removeAttr("checked");
		box.removeClass("ui-state-active");
		icon.removeClass("ui-icon ui-icon-check");
	}
	
	$(checkbox).each(function() {
		if(!this.checked) {
			checkList.push(this);
		}
	})
	
	if(checkList.length == 0) {
		upperBox.attr("checked", "checked");
		box.addClass("ui-state-active");
		icon.addClass("ui-icon ui-icon-check");
	}
	
}

function initCheckAll(e) {
	
	var comp = document.getElementById(e);
	var srt = $(comp);
	var box = srt.find(".cmp_smc_ca .ui-chkbox-box");
	var icon = srt.find(".cmp_smc_ca .ui-chkbox-icon");
	var upper = srt.find(".cmp_smc_ca div input[type=checkbox]");
	var elem = srt.children(".cmp_smc_co").find("input[type=checkbox]");
	var list = [];

	$(elem).each(function() {
		if(!this.checked) {
			list.push(this);
		}
	});

	if(list.length == 0) {
		upper.attr("checked", "checked");
		box.addClass("ui-state-active");
		icon.addClass("ui-icon ui-icon-check");
	}

}