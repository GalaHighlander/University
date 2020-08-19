#include "QtWidgetsApplication1.h"
#include <QtWidgets/QApplication>
#include "GUI.h"
#include "Service.h"
#include "Domain.h"
#include "Tests.h"
#include "Repo.h"
int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    Tests t;
    t.testAll();
    //..QtWidgetsApplication1 w;
    DriverRepo drivers;
    ReportRepo reports;
    DriverService d_service{ drivers };
    ReportService r_service{ reports };
    //w.show();
    for (auto& item : drivers.getDrivers()) {
        GUI* g = new GUI{ item, d_service, r_service };
        g->show();
    }//    g.show();
    return a.exec();
}
