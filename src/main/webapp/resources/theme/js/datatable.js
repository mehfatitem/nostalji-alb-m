function updateDataTableSelectAllCtrl(table) {
	var $table = table.table().node();
	var $chkbox_all = $('tbody input[type="checkbox"]', $table);
	var $chkbox_checked = $('tbody input[type="checkbox"]:checked', $table);
	var chkbox_select_all = $('thead input[name="select_all"]', $table).get(0);

	// If none of the checkboxes are checked
	if ($chkbox_checked.length === 0) {
		chkbox_select_all.checked = false;
		if ('indeterminate' in chkbox_select_all) {
			chkbox_select_all.indeterminate = false;
		}

		// If all of the checkboxes are checked
	} else if ($chkbox_checked.length === $chkbox_all.length) {
		chkbox_select_all.checked = true;
		if ('indeterminate' in chkbox_select_all) {
			chkbox_select_all.indeterminate = false;
		}

		// If some of the checkboxes are checked
	} else {
		chkbox_select_all.checked = true;
		if ('indeterminate' in chkbox_select_all) {
			chkbox_select_all.indeterminate = true;
		}
	}
}

function getArrayLength() {
	arrayLength = 0;
	$(
			"table.dataTable tbody th.dt-body-center, table.dataTable tbody td.dt-body-center")
			.each(function() {
				var className = $(this).closest("tr").attr("class");
				if (className.indexOf('selected') > -1) {
				} else {
					arrayLength++;
				}
			});
	return arrayLength();
}

$(document)
		.ready(
				function() {
					// Array holding selected row IDs
					var rows_selected = [];
					var table = $('#imageTable')
							.DataTable(
									{
										"language" : {
											"url" : "//cdn.datatables.net/plug-ins/1.10.13/i18n/Turkish.json"
										},
										"lengthMenu": [[5 , 10, 25, 50, -1], [5 , 10, 25, 50, "Tümü"]],
										'columnDefs' : [ {
											'targets' : 0,
											'searchable' : false,
											'orderable' : false,
											'width' : '1%',
											'className' : 'dt-body-center',
											'render' : function(data, type,
													full, meta) {
												return '<input type="checkbox">';
											}
										} ],
										'order' : [ [ 1, 'asc' ] ],
										'rowCallback' : function(row, data,
												dataIndex) {
											// Get row ID
											var rowId = data[0];

											// If row ID is in the list of
											// selected row IDs
											if ($.inArray(rowId, rows_selected) !== -1) {
												$(row)
														.find(
																'input[type="checkbox"]')
														.prop('checked', true);
												$(row).addClass('selected');
											}
										}
									});

					// Handle click on checkbox
					$('#imageTable tbody').on('click',
							'input[type="checkbox"]', function(e) {
								var $row = $(this).closest('tr');

								// Get row data
								var data = table.row($row).data();

								// Get row ID
								var rowId = data[0];

								// Determine whether row ID is in the list of
								// selected row IDs
								var index = $.inArray(rowId, rows_selected);

								// If checkbox is checked and row ID is not in
								// list of selected row IDs
								if (this.checked && index === -1) {
									rows_selected.push(rowId);

									// Otherwise, if checkbox is not checked and
									// row ID is in list of selected row IDs
								} else if (!this.checked && index !== -1) {
									rows_selected.splice(index, 1);
								}

								if (this.checked) {
									$row.addClass('selected');
								} else {
									$row.removeClass('selected');
								}

								// Update state of "Select all" control
								updateDataTableSelectAllCtrl(table);

								// Prevent click event from propagating to
								// parent
								e.stopPropagation();
							});

					// Handle click on table cells with checkboxes
					$('#imageTable').on(
							'click',
							'tbody td, thead th:first-child',
							function(e) {
								$(this).parent().find('input[type="checkbox"]')
										.trigger('click');
							});

					// Handle click on "Select all" control
					$('thead input[name="select_all"]',
							table.table().container())
							.on(
									'click',
									function(e) {
										if (this.checked) {
											$(
													'#imageTable tbody input[type="checkbox"]:not(:checked)')
													.trigger('click');
										} else {
											$(
													'#imageTable tbody input[type="checkbox"]:checked')
													.trigger('click');
										}

										// Prevent click event from propagating
										// to parent
										e.stopPropagation();
									});

					// Handle table draw event
					table.on('draw', function() {
						// Update state of "Select all" control
						updateDataTableSelectAllCtrl(table);
					});

					// Handle form submission event
					var selectedArray = [];
					var selectedName = [];
					$(
							"table.dataTable tbody th.dt-body-center, table.dataTable tbody td.dt-body-center")
							.click(
									function() {
										var className = $(this).closest("tr")
												.attr("class");
										var selectedId = $(this).closest("tr")
												.attr("id");
										selectedId = selectedId.replace("tr",
												"");
										if (!className.includes("selected")) {
											selectedArray.push(selectedId);
										} else {
											var index = selectedArray
													.indexOf(selectedId);
											console.log(index);
											if (index > -1) {
												selectedArray.splice(index, 1);
											}
										}
										selectedArray = selectedArray
												.filter(function(elem, pos) {
													return selectedArray
															.indexOf(elem) == pos;
												});
										console.log(selectedArray);
										console.log(className);
									});
					$("#deleteOperation")
							.click(
									function() {
										if (selectedArray.length == 0) {
											sweetAlert("Uyarı!",
													"Lütfen seçim yapınız!",
													"warning");
										} else {
											var imgIds = selectedArray
													.toString();
											for ( var i in selectedArray) {
												selectedName[i] = $(
														"#" + selectedArray[i])
														.text();
											}
											var imgNames = selectedName
													.toString();
											console
													.log(imgIds + " "
															+ imgNames);
											swal(
													{
														title : "Uyarı !",
														text : "Seçili resimleri silmek istediğinizden emin misiniz ?",
														type : "warning",
														showCancelButton : true,
														confirmButtonColor : "#DD6B55",
														confirmButtonText : "Evet",
														cancelButtonText : "Hayır",
														closeOnConfirm : true,
													},
													function(isConfirm) {
														if (isConfirm) {
															$
																	.ajax({
																		type : 'POST',
																		url : 'upload/multipleDelete',
																		data : {
																			imgIds : imgIds,
																			imgNames : imgNames
																		},
																		success : function(
																				data) {
																			if (data == "1") {
																				var length = $("#imageTable tbody tr").length;
																				console
																						.log(length);
																				/*
																				 * if(length ==
																				 * selectedArray.length &&
																				 * length ==
																				 * 0){
																				 * $("#imageTable_wrapper").remove();
																				 * $("#imageTable").remove();
																				 * $("#operations").remove();
																				 * $('body').append("<div
																				 * class='alert
																				 * alert-warning'
																				 * align='center'><strong>Uyarı!</strong>
																				 * Resim
																				 * bulunamadı !</div>");
																				 *  }
																				 */
																				for ( var i in selectedArray) {
																					$(
																							"#tr"
																									+ selectedArray[i])
																							.remove();
																				}
																				location
																						.reload();
																			}
																		}
																	});
														}
													});
										}
									});

				});
