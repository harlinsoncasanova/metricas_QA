isc.Canvas.create({
    ID: "mainCanvas",
    width: "100%",
    height: "100%",
    canDragResize: true
});

isc.VLayout.create({
    ID: "mainLayout",
    width: "100%",
    height: "100%",
    members: [
        isc.HLayout.create({
            ID: "buttonLayout",
            height: 50,
            width: "100%",
            members: [
                isc.Button.create({
                    ID: "registerAppButton",
                    title: "Registrar Aplicación",
                    click: function() {
                        showRegisterForm();
                    }
                }),
                isc.Button.create({
                    ID: "viewSummaryButton",
                    title: "Ver Resúmenes",
                    click: function() {
                        showSummaryForm();
                    }
                })
            ]
        }),
        isc.VLayout.create({
            ID: "formLayout",
            width: "100%",
            height: "100%",
            visible: false
        })
    ]
});

function showRegisterForm() {
    isc.formLayout.setVisible(true);
    isc.summaryLayout.setVisible(false);

    isc.DynamicForm.create({
        ID: "registerForm",
        width: "100%",
        fields: [
            {name: "applicationName", title: "Nombre de la Aplicación", type: "text", required: true},
            {name: "version", title: "Versión", type: "text", required: true}
        ],
        saveButton: isc.Button.create({
            title: "Guardar",
            click: function() {
                alert("Aplicación registrada!");
            }
        })
    }).addTo(formLayout);
}

function showSummaryForm() {
    isc.formLayout.setVisible(false);
    isc.summaryLayout.setVisible(true);

    isc.DynamicForm.create({
        ID: "summaryForm",
        width: "100%",
        fields: [
            {name: "applications", title: "Aplicación", type: "select", valueMap: ["App1", "App2", "App3"], required: true},
            {name: "versions", title: "Versión", type: "select", required: true},
            {name: "testCycles", title: "Ciclo de Pruebas", type: "select", required: true}
        ]
    }).addTo(summaryLayout);
}

isc.Page.create({
    name: "mainPage",
    title: "SmartGWT Interface",
    autoDraw: true
});
