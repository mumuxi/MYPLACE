function SetHome(obj, vrl) {
	try {
		obj.style.behavior = 'url(#default#homepage)';
		obj.setHomePage(vrl);
	} catch (e) {
		if (window.netscape) {
			try {
				netscape.security.PrivilegeManager
						.enablePrivilege("UniversalXPConnect");
			} catch (e) {
				alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
			}
			/*
			 * var prefs =
			 * Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
			 * prefs.setCharPref('browser.startup.homepage', vrl);
			 */
		} else {
			alert("抱歉，您所使用的浏览器无法完成此操作。\n\n您需要手动将" + vrl + "设置为首页。");
		}
	}
}

function submit_bid_form() {
	var price = parseFloat(document.getElementById("bid_price").value);
	if (!isNaN(price)) {
		document.getElementById("bid_form").submit();
	}
}