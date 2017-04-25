app
		.controller(
				"encuesta_caVerCtrl",
				function($scope, $http, $resource, $location, sharedProperties,
						encuesta_caService, tipoCtrlService, mailService,
						$timeout, $uibModal, $log) {

			

					$scope.estadisticasByModuloDto = {};
					$scope.estadisticasByModuloDto.feDesde = new Date();
					$scope.estadisticasByModuloDto.feHasta = new Date();
					$scope.showList = true;
					$scope.userEncuestaDto = {};
					$scope.userEncuestaDtos = [];
					$scope.to = "";
					$scope.cedula = "";
					$scope.cedulaFamiliar = "";
					$scope.smtpUser = "";
					$scope.smtpPassword = "";
					$scope.mailMsg = '';
					$scope.loading = false;

					// /FECHAS
					$scope.defaultValuesCheck = function() {

						if ($scope.estadisticasByModuloDto.swFeDesde) {
							if (angular
									.isDate($scope.estadisticasByModuloDto.feDesde)) {
								$scope.estadisticasByModuloDto.swFeDesde = false;
								$scope.estadisticasByModuloDto.feHasta = null;

							}
						}
						if ($scope.estadisticasByModuloDto.swFeHasta) {
							if (angular
									.isDate($scope.estadisticasByModuloDto.feHasta)) {
								$scope.estadisticasByModuloDto.swFeHasta = false;

							}
						}
						if (validaFechaClick()) {
							$scope.graficar();
						}
					};

					$scope.defaultValuesNullDesdeCheck = function() {
						$scope.estadisticasByModuloDto.feDesde = null;
						$scope.estadisticasByModuloDto.feHasta = null;
					}
					$scope.defaultValuesNullHastaCheck = function() {
						$scope.estadisticasByModuloDto.feHasta = null;
					}

					$scope.today = function() {

						if ($scope.estadisticasByModuloDto.swFeDesde) {
							$scope.estadisticasByModuloDto.feDesde = new Date();
							$scope.estadisticasByModuloDto.swFeDesde = false;
							$scope.estadisticasByModuloDto.feHasta = null;
						}
						if ($scope.estadisticasByModuloDto.swFeHasta) {
							$scope.estadisticasByModuloDto.feHasta = new Date();
							$scope.estadisticasByModuloDto.swFeHasta = false;
						}
						if (validaFechaClick()) {
							$scope.graficar();
						}
						$scope.defaultValuesCheck();
					};

					validaFechaClick = function() {
						var isGraficar = false;
						var fechaDesde = $scope.estadisticasByModuloDto.feDesde;
						var feHasta = $scope.estadisticasByModuloDto.feHasta;
						document.getElementById("chartContainer").innerHTML = '&nbsp;';

						if (null != fechaDesde && null != feHasta) {
							if (fechaDesde.getTime() > feHasta.getTime()) {
								alert('Error en fecha.');
								$scope.estadisticasByModuloDto.feHasta = null;
								$scope.estadisticasByModuloDto.feDesde = null;
								isGraficar = false;
							} else {
								isGraficar = true;
							}
						}
						return isGraficar;
					};

					$scope.buscarMedicamento = function(filtro) {
						$scope.mensajeMedicDisponiblesOut = "";
						encuesta_caService
								.buscarCedulaMedicamento(
										{
											cedula : filtro
										},
										function success(data, status) {
											$scope.userEncuestaDto = data;

										},
										function err(httpResponse) {
											$scope.aviso = 'Ha pasado algo inesperado encuesta_caService.js->buscarMedicamento';
										});
					}

					$scope.graficar = function graficar() {
						/** **Usuuarios******** */
						// $scope.buscarMedicamento();
						// alert('desde='+$scope.estadisticasByModuloDto.feDesde+',hasta='+$scope.estadisticasByModuloDto.feHasta);
						// alert(''+ new Date());
						/** *Buscamos atencion****Grafico******** */
						encuesta_caService
								.graficar(
										{
											interesdos : 'ENCUESTA MEDICA',
											feDesde : $scope.estadisticasByModuloDto.feDesde,
											feHasta : $scope.estadisticasByModuloDto.feHasta
										},
										function success(chartDto, status) {

											document
													.getElementById("chartContainer").innerHTML = '&nbsp;';
											document
													.getElementById("chartContainer").innerHTML = '<canvas id="myChart"></canvas>';
											var ctx = document.getElementById(
													"myChart").getContext("2d");
											/** ****GRAFICAMOS******* */
											/** Inicializamos grafico */
											myChart = {};
											/** Fin grafico */

											myChart = new Chart(ctx, chartDto);
										},
										function err(httpResponse) {
											$scope.status = status;
											$scope.aviso = 'Ha pasado algo inesperado inscripcionByModuloCtrl.js->asistenciaService.byModulo';
										});
					}

					/** ********Main********************* */
					$scope.graficar();

					/** ********End Main********************* */

					/** *********POP-PUP*************** */
					$scope.popup = function(items) {
						angular.forEach(items, function(item) {
							console.log(item.tx_parametro_nom);

						});

						$scope.openModal('sm', "Detalles", items);
					}

					$scope.openModal = function(size, msg, arreglo) {

						tipoCtrlService.msg = msg;
						var modalInstance = $uibModal.open({
							animation : $scope.animationsEnabled,
							templateUrl : 'myModalContent.html',
							controller : 'ModalInstanceCtrl',
							size : size,
							resolve : {
								items : function() {

									return arreglo;
								}
							}
						});
						modalInstance.result.then(function(selectedItem) {

							/** Borramos todos los seleccionados */
							// alert('Awesome!');
						}, function() {
							$log.info('Modal dismissed at: ' + new Date());
						});
					};

					/** *****END****POP-PUP*************** */

					$scope.updateDatabase = function(cedula, cedulaFamiliar) {
						$http({
							url : 'reporte',
							method : "GET",
							params : {
								'cedula' : cedula,
								'cedulaFamiliar' : cedulaFamiliar
							}
						});

					};
					$scope.generarReporte = function(cedula, cedulaFamiliar) {
						var campo = document.myForm;
						campo.action = "reporte";
						campo.cedula.value = cedula;
						campo.cedulaFamiliar.value = cedulaFamiliar;
						$scope.mailMsg = '';
						campo.submit();
					};

					$scope.generarReporteByfecha = function() {
						var campo = document.myForm;
						campo.action = "reporteServletByFecha";

						campo.fechaDesdeR.value = $scope.estadisticasByModuloDto.feDesde;
						campo.fechaHastaR.value = $scope.estadisticasByModuloDto.feHasta;
						campo.submit();
					};

					$scope.viewEncuesta = function() {
						$scope.to = "";
						$scope.cedula = "";
						$scope.cedulaFamiliar = "";
						$scope.smtpUser = '';
						$scope.smtpPassword = '';
						$scope.message = '';
						$scope.showList = true;
						$scope.mailMsg = '';
						return $scope.showList;
					};

					$scope.enviarMail = function() {

						$scope.loading = true;

						mailService
								.sendMail(
										{
											to : $scope.to,
											cedula : $scope.cedula,
											cedulaFamiliar : $scope.cedulaFamiliar,
											smtpUser : $scope.smtpUser,
											smtpPassword : $scope.smtpPassword,
											message : $scope.message,
											title : 'ENCUESTA MEDICA'
										},
										function success(data, status) {
											result = data.stRecibirMedicam;
											$scope.loading = false;
											$scope.mailMsg = 'El correo fue enviado.'
											if (result === 'no') {
												$scope.mailMsg = 'El correo no se pudo enviar.'
											}

										},
										function err(httpResponse) {
											$scope.loading = false;
											$scope.aviso = 'Ha pasado algo inesperado encuesta_caService.js->buscarMedicamento';
										});

						$scope.showList = false;
						return $scope.showList;
					};

					$scope.viewMail = function(cedula, cedulaFamiliar, email) {
						$scope.to = email;
						$scope.cedula = cedula;
						$scope.cedulaFamiliar = cedulaFamiliar;
						$scope.smtpUser = '';
						$scope.smtpPassword = '';
						$scope.message = '';
						$scope.mailMsg = '';
						$scope.showList = false;

						return $scope.showList;
					};

					/** *********Websocket*********************** */
					
					
					/** *********Websocket*********************** */

					window.onload = init;
					init();
					var socket = new WebSocket(
							"ws://localhost:8080/encuesta/actions");
					socket.onmessage = onMessage;
					/** *********End Websocket*********************** */
					
					// When the connection is open, send some data to the server
					socket.onopen = function () {
						alert('Connect');
						console.log('Connect ');
					//	socket.send('Ping'); // Send the message 'Ping' to the server
					};

					// Log errors
					socket.onerror = function (error) {
						alert('Error');
					  console.log('WebSocket Error ' + error);
					};

					

					function onMessage(event) {
					 
					 
							var device = JSON.parse(event.data);
							if (device.action === "add") {
								printDeviceElement(device);
							}
							if (device.action === "remove") {
								document.getElementById(device.id).remove();
								// device.parentNode.removeChild(device);
							}
							if (device.action === "toggle") {
								var node = document.getElementById(device.id);
								var statusText = node.children[2];
								if (device.status === "On") {
									statusText.innerHTML = "Status: "
											+ device.status
											+ " (<button ng-click=toggleDevice("
											+ device.id
											+ ")>Turn off</button>)";
								} else if (device.status === "Off") {
									statusText.innerHTML = "Status: "
											+ device.status
											+ " (<button  ng-click=toggleDevice("
											+ device.id + ")>Turn on</button>)";
								}
							}
							socket.close();
					}

					$scope.addDevice = function addDevice(name, type,
							description) {
						var DeviceAction = {
							action : "add",
							name : name,
							type : type,
							description : description
						};
						socket.send(JSON.stringify(DeviceAction));
					}

					$scope.removeDevice = function removeDevice(element) {
						alert('1');
						var id = element;
						var DeviceAction = {
							action : "remove",
							id : id
						};
						socket.send(JSON.stringify(DeviceAction));
						alert('2');
					}

					$scope.toggleDevice = function toggleDevice(element) {
						var id = element;
						var DeviceAction = {
							action : "toggle",
							id : id
						};
						socket.send(JSON.stringify(DeviceAction));
					}

					function printDeviceElement(device) {
						var content = document.getElementById("content");

						var deviceDiv = document.createElement("div");
						deviceDiv.setAttribute("id", device.id);
						deviceDiv
								.setAttribute("class", "device " + device.type);
						content.appendChild(deviceDiv);

						var deviceName = document.createElement("span");
						deviceName.setAttribute("class", "deviceName");
						deviceName.innerHTML = device.name;
						deviceDiv.appendChild(deviceName);

						var deviceType = document.createElement("span");
						deviceType.innerHTML = "<b>Type:</b> " + device.type;
						deviceDiv.appendChild(deviceType);

						var deviceStatus = document.createElement("span");
						if (device.status === "On") {
							// <a id="link-2" href="" ng-click="showForm()">Add
							// a device</a>
							deviceStatus.innerHTML = "<b>Status:</b> "
									+ device.status
									+ " (<button ng-click=toggleDevice("
									+ device.id + ")>Turn off</button>)";
						} else if (device.status === "Off") {
							deviceStatus.innerHTML = "<b>Status:</b> "
									+ device.status
									+ " (<button ng-click=toggleDevice("
									+ device.id + ")>Turn on</button>)";
							// deviceDiv.setAttribute("class", "device off");
						}
						deviceDiv.appendChild(deviceStatus);

						var deviceDescription = document.createElement("span");
						deviceDescription.innerHTML = "<b>Comments:</b> "
								+ device.description;
						deviceDiv.appendChild(deviceDescription);

						var removeDevice = document.createElement("span");
						removeDevice.setAttribute("class", "removeDevice");
						removeDevice.innerHTML = "<button ng-click=\"removeDevice("
								+ device.id + ")\">Remove device</button>";
						deviceDiv.appendChild(removeDevice);
					}

					$scope.showForm = function showForm() {
						document.getElementById("addDeviceForm").style.display = '';
					}

					$scope.hideForm = function() {
						document.getElementById("addDeviceForm").style.display = "none";
					}

					$scope.formSubmit = function formSubmit() {
						var form = document.getElementById("addDeviceForm");
						var name = form.elements["device_name"].value;
						var type = form.elements["device_type"].value;
						var description = form.elements["device_description"].value;
						// $scope.hideForm();
						document.getElementById("addDeviceForm").style.display = "none";
						document.getElementById("addDeviceForm").reset();
						$scope.addDevice(name, type, description);
					}

					function init() {
						// $scope.hideForm();
						document.getElementById("addDeviceForm").style.display = "none";
					}
					/** *******end websocket********************************** */

				});
