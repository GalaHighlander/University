#pragma once
#include <QPushButton>
#include <QListWidget>
#include <QString>
#include <QLineEdit>
#include <QHBoxLayout>
#include <qlistwidget.h>
//#include "Domain.h"
//#include "Service.h"
#include <qslider.h>
#include <QDialog>
#include <qwidget.h>
#include <QCheckBox>
#include "Domain.h"
#include "Service.h"
#include <QTableView>
#include <qlabel.h>
//#include "Observer.h"
class GUI : public QWidget
{
	Q_OBJECT
public:
	void init();
	GUI(Driver& _d,DriverService& dr, ReportService& r, QWidget* parent = Q_NULLPTR);


private:
	Driver d;
	DriverService& driverService;
	ReportService& reportService;
	//QLabel* location_score_status;
	QLabel* location_long;
	QSlider* slider;
	void addButtonHandler();
	QLabel* location_lat;
	QPushButton* validateReportButton;
	QHBoxLayout* hLayout;
	QListWidget* reportList;
	QPushButton* addReport;
	QLineEdit* description;
	QLineEdit* longitude;
	QLineEdit* latitude;
	void sliderHandler();
	void connectButtons();
	void validateButtonHandler();
	void populateList();
	int getSelectedIndex();
	void northHandler();
	void southHandler();
	void westHandler();
	void eastHandler();
	QPushButton* north;
	QPushButton* south;
	QPushButton* east;
	QPushButton* west;
};

