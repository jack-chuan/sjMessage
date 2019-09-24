/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.image_previewText='';
	config.removeDialogTabs = 'image:advanced;image:Link';
	config.filebrowserUploadUrl = "/oa/governmentAffairsInfo/impnews.json?action=uploadImg";
	config.font_names='宋体/SimSun;新宋体/NSimSun;仿宋_GB2312/FangSong_GB2312;楷体_GB2312/KaiTi_GB2312;黑体/SimHei;微软雅黑/Microsoft YaHei;'+ config.font_names;
	config.pasteFromWordRemoveFontStyles = false;
	config.pasteFromWordRemoveStyles = false;
	config.allowedContent = true;
	config.format_p = { element: 'p', attributes: { 'class': 'normalPara' } };
	// config .widht='700px';
	config .heigh='600px';
	//config.disallowedContent = 'img{width,height};img[width,height]';
	config.toolbar = 'Full';
	config.toolbar_Full = [
        // { name: 'document', items : [ 'Source','-','Save','NewPage','DocProps','Preview','Print','-','Templates' ] },
        // { name: 'clipboard', items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ] },
        // { name: 'editing', items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ] },
        // { name: 'forms', items : [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton','HiddenField' ] },
        // '/',
        { name: 'basicstyles', items : [ 'Bold','Italic','Underline','Strike','-','CopyFormatting','RemoveFormat' ] },
        { name: 'paragraph', items : [ 'JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'] },
        // { name: 'links', items : [ 'Link','Unlink','Anchor' ] },
        // { name: 'insert', items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak','Iframe' ] },
        // '/',
        { name: 'styles', items : [ 'Styles','Format','Font','FontSize' ] },
        { name: 'colors', items : [ 'TextColor','BGColor' ] }
        // { name: 'tools', items : [ 'Maximize', 'ShowBlocks','-','About' ] }
    ];
};
//删除高级TAB 和 图像信息里的 基本表单
CKEDITOR.on('dialogDefinition',function (ev) {
	var dialogName = ev.data.name;
    var dialogDefinition = ev.data.definition;
    console.log(ev.data.definition.getContents("info"));
    if (dialogName == 'imagebutton') {
   	 //当然可以为其他的东西的，img啊，flash啊，不知道的话自己写个alert(dialogName)就可与测试出来了
        dialogDefinition.removeContents('advanced'); //消除advanced标签
        ev.data.definition.getContents("info").remove( 'txtBorder' );
        ev.data.definition.getContents("info").remove( 'txtHSpace' );
        ev.data.definition.getContents("info").remove( 'txtVSpace' );
        ev.data.definition.getContents("info").remove( 'cmbAlign' );
    }
});