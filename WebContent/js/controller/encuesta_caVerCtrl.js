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
					
					$scope.generarReporteExcelByfecha = function() {
						var campo = document.myForm;
						campo.action = "reporteServletExcelByFecha";

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

				 

				});
