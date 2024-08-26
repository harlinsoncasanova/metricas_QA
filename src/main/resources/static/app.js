// Crear los botones
isc.HLayout.create({
    ID: "buttonLayout",
    width: "100%",
    height: 50,
    members: [
        isc.Button.create({
            title: "Registrar Aplicación",
            click: function () {
                showRegistrationForm();
            }
        }),
        isc.Button.create({
            title: "Ver Resúmenes",
            click: function () {
                showSummaryForm();
            }
        })
    ]
}).placeNear("buttons");

// Mostrar el formulario de registro de aplicación
function showRegistrationForm() {
    isc.DynamicForm.create({
        ID: "registrationForm",
        width: 400,
        fields: [
            {name: "appName", title: "Nombre de la Aplicación", type: "text", required: true}
        ],
        saveData: function () {
            // Lógica para guardar la aplicación
            fetch('/api/v1/applications', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.getValues())
            })
                .then(response => response.json())
                .then(data => {
                    isc.say("Aplicación registrada exitosamente.");
                    // Limpiar el formulario
                    this.clearValues();
                })
                .catch((error) => {
                    console.error('Error:', error);
                });
        }
    }).placeNear("formContainer");
}

// Mostrar el formulario de resumenes
function showSummaryForm() {
    isc.VLayout.create({
        ID: "summaryLayout",
        width: "100%",
        height: "100%",
        members: [
            isc.SelectItem.create({
                ID: "appSelect",
                title: "Seleccionar Aplicación",
                fetchData: function() {
                    // Lógica para obtener las aplicaciones desde el backend
                    fetch('/api/v1/applications')
                        .then(response => response.json())
                        .then(data => {
                            this.setValueMap(data.map(app => ({ value: app.id, name: app.name })));
                        });
                },
                changed: function () {
                    const appId = this.getValue();
                    loadVersions(appId);
                }
            }),
            isc.SelectItem.create({
                ID: "versionSelect",
                title: "Seleccionar Versión",
                changed: function () {
                    const versionId = this.getValue();
                    loadTests(versionId);
                }
            }),
            isc.SelectItem.create({
                ID: "testSelect",
                title: "Seleccionar Test",
                changed: function () {
                    const testId = this.getValue();
                    loadMetrics(testId);
                }
            }),
            isc.DynamicForm.create({
                ID: "metricsForm",
                width: 600,
                fields: [
                    {name: "metric1", title: "Metric 1", type: "text"},
                    {name: "metric2", title: "Metric 2", type: "text"},
                    {name: "metric3", title: "Metric 3", type: "text"},
                    {name: "metric4", title: "Metric 4", type: "text", disabled: true},
                    {name: "metric5", title: "Metric 5", type: "text", disabled: true}
                ]
            })
        ]
    }).placeNear("summaryContainer");
}

// Función para cargar versiones según la aplicación seleccionada
function loadVersions(appId) {
    fetch(`/api/v1/applications/${appId}/versions`)
        .then(response => response.json())
        .then(data => {
            isc.ByID("versionSelect").setValueMap(data.map(version => ({ value: version.id, name: version.name })));
        });
}

// Función para cargar tests según la versión seleccionada
function loadTests(versionId) {
    fetch(`/api/v1/versions/${versionId}/tests`)
        .then(response => response.json())
        .then(data => {
            isc.ByID("testSelect").setValueMap(data.map(test => ({ value: test.id, name: test.name })));
        });
}

// Función para cargar métricas según el test seleccionado
function loadMetrics(testId) {
    fetch(`/api/v1/tests/${testId}/metrics`)
        .then(response => response.json())
        .then(data => {
            const metricsForm = isc.ByID("metricsForm");
            metricsForm.setValues({
                metric1: data[0].value,
                metric2: data[1].value,
                metric3: data[2].value,
                metric4: calculateMetric4(data),
                metric5: calculateMetric5(data)
            });
        });
}

// Ejemplo de cálculos de métricas adicionales
function calculateMetric4(metrics) {
    // Implementar la lógica de cálculo
    return (metrics[0].value + metrics[1].value) / 2;
}

function calculateMetric5(metrics) {
    // Implementar la lógica de cálculo
    return metrics[2].value * 1.1;
}
