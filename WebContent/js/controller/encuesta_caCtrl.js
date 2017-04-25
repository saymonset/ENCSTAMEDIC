app
		.controller(
				"encuesta_caCtrl",
				function($scope, $http, $resource, $location,
						encuesta_caService, $timeout) {
					
					
				
					$scope.fam = null;
					  $scope.familiares = null;

					
					
					$scope.cantidades = [];
					$scope.cantidadesCall = function(){
						$scope.cantidades = [];	
						for (var i = 1; i<=100; i++){
							$scope.cantidades.push(i);
						}
					}
					$scope.buscarMedicinasShowPdf = function() {
						// Buscamos las medicinas.. si tiene, mostramos el pdf
						encuesta_caService
						.parametroNominaDtos(
								{
									cedula : $scope.data.nuCedula,
									nuCedulaFamiliar : $scope.data.nuCedulaFamiliar
								},
								function success(data, status) {
									$scope.medicinas = data;
								},
								function err(httpResponse) {

									$scope.aviso = 'Ha pasado algo inesperado validaCedulaAndFamiliar';
								});
						}
					

					/**LLammaos a cargar las cantidades*/
					$scope.cantidadesCall();
					
					$scope.filtro = "";
					/** Lista de medicamentos */
					$scope.llenarLista = function() {
						$timeout(
								function() {
									encuesta_caService
											.query(
													{
														co_proceso_nom : 'ENCU'
													},
													function success(data,
															status) {
														$scope.benefMedicamentoDto = data;
													},
													function err(httpResponse) {

														$scope.status = status;
														$scope.aviso = 'Ha pasado algo inesperado encuesta_caService.js->encuesta_caService.query';
													});
								}, 50);

					}

					$scope.benefMedicamentoDto = {};
					$scope.benefMedicamentoDisponiblesDto = {};
					$scope.encuestas = {};
					$scope.showValidationMail = true;
					$scope.mensajeOut = "";
					$scope.mensajeMedicDisponiblesOut = "";
					$scope.userEncuestaDto = {};
					$scope.mensajeEmpleadoErrorOut = '';
					$scope.resultBoleanDto = {};
					$scope.resultBoleanDto.existe = false;
					/** Lista de medicamentos */
					$scope.llenarLista();
					$scope.filtro = "";
					
					

					$scope.buscarMedicamento = function(filtro) {
						$scope.mensajeMedicDisponiblesOut = "";
						$scope.filtro = "";
						encuesta_caService
								.buscarMedicamento(
										{
											co_proceso_nom : filtro
										},
										function success(data, status) {
											$scope.benefMedicamentoDisponiblesDto = data;

											if (typeof $scope.benefMedicamentoDisponiblesDto.parametroNominaDtos == 'undefined' || $scope.benefMedicamentoDisponiblesDto.parametroNominaDtos.length == 0) {
												$scope.mensajeMedicDisponiblesOut = "No existe para "+filtro;
											}
										},
										function err(httpResponse) {
											$scope.aviso = 'Ha pasado algo inesperado encuesta_caService.js->buscarMedicamento';
										});
					}
					
					$scope.cancelar = function() {
						$scope.data.stRecibirMedicam = null;		
					}
					
					$scope.generarReporte = function(cedula, cedulaFamiliar) {
						var campo = document.myForm;
						campo.action = "reporte";
						campo.cedula.value = cedula;
						campo.cedulaFamiliar.value = cedulaFamiliar;
						$scope.mailMsg = '';
						campo.submit();
					};

					$scope.seleccionar = function(item) {
						var count = 0;
						angular
								.forEach(
										$scope.benefMedicamentoDisponiblesDto.parametroNominaDtos,
										function(item) {
											if (item.checked) {
												$scope.benefMedicamentoDto.parametroNominaDtos
														.push(item);
												$scope.benefMedicamentoDisponiblesDto.parametroNominaDtos
														.splice(
																$scope.benefMedicamentoDisponiblesDto.parametroNominaDtos
																		.indexOf(item),
																1);
											}

										});
					}
					
					$scope.desSeleccionar = function(item) {
						var count = 0;
						
						if (typeof $scope.benefMedicamentoDisponiblesDto.parametroNominaDtos == 'undefined' || $scope.benefMedicamentoDisponiblesDto.parametroNominaDtos.length == 0) {
							$scope.benefMedicamentoDisponiblesDto.parametroNominaDtos = [];
						}
						
						angular
								.forEach(
										$scope.benefMedicamentoDto.parametroNominaDtos,
										function(item) {
											if (!item.checked) {
												$scope.benefMedicamentoDisponiblesDto.parametroNominaDtos
														.push(item);
												$scope.benefMedicamentoDto.parametroNominaDtos
														.splice(
																$scope.benefMedicamentoDto.parametroNominaDtos
																		.indexOf(item),
																1);
											}

										});
					}

					$scope.regresar = function() {
						$scope.benefMedicamentoDisponiblesDto = {};
						$scope.benefMedicamentoDisponiblesDto.parametroNominaDtos = [];
						$scope.benefMedicamentoDto = {};
						$scope.benefMedicamentoDto.parametroNominaDtos =[];
						$scope.encuestas = {};
						$scope.showValidationMail = true;
						$scope.mensajeOut = "";
						$scope.userEncuestaDto = {};
						$scope.mensajeEmpleadoErrorOut = '';
						$scope.resultBoleanDto = {};
						$scope.resultBoleanDto.existe = false;
						$scope.data.nuCedula = '';
						$scope.data.nuCedulaFamiliar = '';
						$scope.data.txEmail = '';
						$scope.data.coypTxEmail = '';
						$scope.data.stRecibirMedicam = null;
						/** Lista de medicamentos */
						$scope.llenarLista();

					}

					$scope.incompleteCount = function() {
						var count = 0;
						angular.forEach(
								$scope.benefMedicamentoDto.parametroNominaDtos,
								function(item) {
									if (item.checked) {
										count++
									}
								});
						return count;
					}

					// personaYaEncuestada
					$scope.personaYaEncuestada = function() {
						encuesta_caService
								.parametroNominaDtos(
										{
											cedula : $scope.data.nuCedula,
											nuCedulaFamiliar : $scope.data.nuCedulaFamiliar
										},
										function success(data, status) {
											$scope.benefMedicamentoDto.parametroNominaDtos = data;
											
											angular.forEach(
													$scope.benefMedicamentoDto.parametroNominaDtos,
													function(item) {
														item.checked = true;
													});
											
											
											
										},
										function err(httpResponse) {

											$scope.aviso = 'Ha pasado algo inesperado encuesta_caService.js->encuesta_caService.validaCedulaAndFamiliar';
										});
					}

					// validando cedula empleado
					$scope.cedulaEmpl = function() {
						encuesta_caService
								.findUser(
										{
											cedula : $scope.data.nuCedula
										},
										function success(data, status) {
											$scope.data.nuCedulaFamiliar = '';
											$scope.mensajeEmpleadoErrorOut = '';
											$scope.userEncuestaDto = data;
											if ($scope.userEncuestaDto != null
													&& $scope.userEncuestaDto.nombre != null) {
												
												
												/**Obtenemos familiares*/
												$scope.data.nuCedulaFamiliar = $scope.userEncuestaDto.cedula;
												$scope.data.nombreFamiliar = $scope.userEncuestaDto.nombre;
												$scope.familiaresObtener();
												
												/**Si tiene medicinas, mostramos el pdf*/
												$scope.buscarMedicinasShowPdf();
											}

										},
										function err(httpResponse) {
											$scope.data = data || "FALSE";
											$scope.status = status;
											$scope.aviso = 'Ha pasado algo inesperado encuesta_caService.js->encuesta_caService.findUser';
										});
					}
					
					
					
					$scope.familiaresObtener = function(){
						encuesta_caService
						.familiares(
								{
									cedula : $scope.data.nuCedula
								},
								function success(data, status) {
									$scope.familiares = data; 								 

								},
								function err(httpResponse) {
									$scope.aviso = 'Ha pasado algo inesperado encuesta_caService.js->familiaresObtener';
								});
					}
					

					// validando cedula familiar
					$scope.cedulaSameFamiliar = function(userEncuestaDto) {
						if (null != userEncuestaDto){
							//$scope.data.nuCedulaFamiliar = userEncuestaDto.cedula	
							$scope.data.nuCedulaFamiliar =userEncuestaDto.cedula;
							$scope.data.nombreFamiliar = userEncuestaDto.nombre;
						}
						
						$scope.mensajeEmpleadoErrorOut = '';
						if ($scope.userEncuestaDto == null
								|| $scope.userEncuestaDto.nombre == null) {
							$scope.data.nuCedulaFamiliar = '';
							$scope.mensajeEmpleadoErrorOut = 'La cedula del trabajador '
									+ $scope.data.nuCedula
									+ ' no se encontro en la base de datos.';
						}else{
							if ($scope.data!=null && $scope.data.nuCedulaFamiliar != null){
								
								/**Si tiene medicinas, mostramos el pdf*/
								$scope.buscarMedicinasShowPdf();								
							}
						}
						$scope.otroFamiliar = false;
					}
					
					
					
					
					

					$scope.confirmarCorreo = function() {
						$scope.showValidationMail = false;
						if ($scope.data.coypTxEmail == $scope.data.txEmail) {
							$scope.showValidationMail = true;
						}

					}

					$scope.saveData = function() {
						for (var i = 0; i < $scope.benefMedicamentoDto.parametroNominaDtos.length; i++) {
							$scope.benefMedicamentoDto.parametroNominaDtos[i].txEmail = $scope.data.txEmail;
							$scope.benefMedicamentoDto.parametroNominaDtos[i].nuCedula = $scope.data.nuCedula;
							$scope.benefMedicamentoDto.parametroNominaDtos[i].nuCedulaFamiliar = $scope.data.nuCedulaFamiliar;
							$scope.benefMedicamentoDto.parametroNominaDtos[i].stRecibirMedicam = $scope.data.stRecibirMedicam;
						}

						$http
								.put(
										"services/encuesta_caService/save",
										angular
												.toJson($scope.benefMedicamentoDto.parametroNominaDtos))
								.success(
										function(dataOut) {
											if (dataOut != null) {
												/**GENERAMOS DE FORMA AUTOMATICA  EL PDF*/
												$scope.generarReporte( $scope.data.nuCedula, $scope.data.nuCedulaFamiliar);
												$scope.medicinas = [];
												/**FIN GENERAMOS DE FORMA AUTOMATICA  EL PDF*/
												$scope.mensajeOut = "Muchas Gracias Por su Participación en la encuesta Seguridad y Salud.";

												encuesta_caService
														.graficar(
																{
																	interesdos : 'ENCUESTA MEDICA'
																},
																function success(
																		chartDto,
																		status) {

																	document
																			.getElementById("chartContainer").innerHTML = '&nbsp;';
																	document
																			.getElementById("chartContainer").innerHTML = '<canvas id="myChart"></canvas>';
																	var ctx = document
																			.getElementById(
																					"myChart")
																			.getContext(
																					"2d");
																	/** ****GRAFICAMOS******* */
																	/**
																	 * Inicializamos
																	 * grafico
																	 */
																	myChart = {};
																	/**
																	 * Fin
																	 * grafico
																	 */
																	myChart = new Chart(
																			ctx,
																			chartDto);
																},
																function err(
																		httpResponse) {
																	$scope.status = status;
																	$scope.aviso = 'Ha pasado algo inesperado inscripcionByModuloCtrl.js->asistenciaService.byModulo';
																});

											} else {
												$scope.mensajeOut = "Hubo un error inesperado";
											}

										});

					}

					/** Gravamos los datos y sacamos la grafica */
					$scope.loadDataNo = function() {

						$scope.benefMedicamentoDto = {};
						$timeout(
								function() {
									encuesta_caService
											.queryNo(
													{
														co_proceso_nom : 'ENCU',
														tx_parametro_nom : 'NO'
													},
													function success(data,
															status) {

														$scope.benefMedicamentoDto = data;
														for (var i = 0; i < $scope.benefMedicamentoDto.parametroNominaDtos.length; i++) {
															$scope.benefMedicamentoDto.parametroNominaDtos[i].txEmail = $scope.data.txEmail;
															$scope.benefMedicamentoDto.parametroNominaDtos[i].nuCedula = $scope.data.nuCedula;
															$scope.benefMedicamentoDto.parametroNominaDtos[i].nuCedulaFamiliar = $scope.data.nuCedulaFamiliar;
															$scope.benefMedicamentoDto.parametroNominaDtos[i].stRecibirMedicam = $scope.data.stRecibirMedicam;
														}
														$scope.saveData();

													},
													function err(httpResponse) {
														$scope.data = data
																|| "FALSE";
														$scope.status = status;
														$scope.aviso = 'Ha pasado algo inesperado encuesta_caService.js->encuesta_caService.query';
													});
								}, 50);

					}

					/** Gravamos los datos y sacamos la grafica */
					$scope.loadData = function() {

						$scope.saveData();
					}
				});
