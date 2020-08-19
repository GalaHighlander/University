#include "GUI.h"


#include <qmessagebox.h>
#include <qlistwidget.h>
void GUI::init()
{
	this->setWindowTitle(QString::fromStdString(d.getName()));
	this->hLayout = new QHBoxLayout{ this };
	this->location_long = new QLabel{};
	this->location_lat = new QLabel{};
	this->slider = new QSlider{};
	this->slider->setValue(10);
	this->hLayout->addWidget(slider);
	this->location_long->setText(QString::fromStdString(d.toString()));
	this->hLayout->addWidget(location_long);
	this->reportList = new QListWidget{};
	this->hLayout->addWidget(reportList);
	this->addReport = new QPushButton{};
	this->description = new QLineEdit{};
	this->longitude = new QLineEdit{};
	this->latitude = new QLineEdit{};
	this->description->setText("Descr");
	this->longitude->setText("Longitude");
	this->latitude->setText("Latutude");
	this->addReport->setText("AddReport");
	this->hLayout->addWidget(addReport);
	this->hLayout->addWidget(description);
	this->hLayout->addWidget(longitude);
	this->validateReportButton = new QPushButton{};
	this->validateReportButton->setText("Validate selected!");
	this->hLayout->addWidget(latitude);
	this->hLayout->addWidget(validateReportButton);
	this->north = new QPushButton{};
	this->south = new QPushButton{};
	this->west = new QPushButton{};
	this->east = new QPushButton{};
	this->north->setText("North");
	this->south->setText("South");
	this->west->setText("west");
	this->east->setText("East");
	this->hLayout->addWidget(north);
	this->hLayout->addWidget(south);
	this->hLayout->addWidget(east);
	this->hLayout->addWidget(west);
	this->populateList();
	this->connectButtons();
}

GUI::GUI(Driver& _d, DriverService& dr, ReportService& r, QWidget* parent): QWidget(parent), d{_d}, driverService{dr}, reportService{r}
{
	this->init();
	
}

void GUI::addButtonHandler()
{

	/// <summary>
	/// this function get the info from the labels and sends them to the service to create the object
	/// </summary>
	std::string reporter = d.getName();
	int longitude = this->longitude->text().toInt();
	int latitude = this->latitude->text().toInt();
	try {
		if (d.getLocation().first - longitude > 20 || d.getLocation().second - latitude > 20 || this->description->text() == "")
			throw std::exception("Something went wrong!");
		this->reportService.add(this->description->text().toStdString(), reporter, latitude, longitude, false);
		this->populateList();
	}
	catch (exception& e) {
		QMessageBox msg;
		msg.setText(e.what());
		msg.exec();
	}


}

void GUI::sliderHandler()
{
	int value = this->slider->value();
	this->d.setLocation(value, value);
	this->location_long->setText(QString::fromStdString(d.toString()));

	this->populateList();
}

void GUI::connectButtons()
{
	QObject::connect(this->addReport, &QPushButton::clicked, this, &GUI::addButtonHandler);
	QObject::connect(this->validateReportButton, &QPushButton::clicked, this, &GUI::validateButtonHandler);
	QObject::connect(this->north, &QPushButton::clicked, this, &GUI::northHandler);

	QObject::connect(this->south, &QPushButton::clicked, this, &GUI::southHandler);

	QObject::connect(this->west, &QPushButton::clicked, this, &GUI::westHandler);
	QObject::connect(this->east, &QPushButton::clicked, this, &GUI::eastHandler);
	QObject::connect(this->slider, &QSlider::valueChanged, this, &GUI::sliderHandler);


}

void GUI::validateButtonHandler()
{
	int index = this->getSelectedIndex();
	//Report r = this->reportService.getReports()[index];
	for (auto& item : this->driverService.getRepo())
	{
		if (item.getName() == this->reportService.getReports()[index].getRep() && this->d.getName() != this->reportService.getReports()[index].getRep())
		{
			item.setScore();
			this->reportService.getReports()[index].setTrue();
			this->driverService.write();
			this->populateList();
		}
		if (this->d.getScore() == 2) {
			item.setStatus("regular");
			//this->location_long->setText(QString::fromStdString(item.toString()));
			this->driverService.write();

		}
		if (this->d.getScore() > 2)
		{
			item.setStatus("advanced");
			this->driverService.write();
			//this->location_long->setText(QString::fromStdString(item.toString()));
			
		}
	}
}

void GUI::populateList()
{
	if (this->reportList->count() > 0)
		this->reportList->clear();

	try {
		for (auto item : this->reportService.getReports()) {
			if ((d.getLocation().first - item.getLocation().first) <= 10 && (d.getLocation().second - item.getLocation().second) <= 10) {
				QString itemInList = QString::fromStdString(item.toString());
				QListWidgetItem* adder = new QListWidgetItem{ itemInList };
				this->reportList->addItem(adder);
			}
		}

	}
	catch (exception& e) {
		QMessageBox msg;
		msg.setText(e.what());
		msg.show();
	}

	if (this->reportService.getReports().size() > 0)
		this->reportList->setCurrentRow(0);
}

int GUI::getSelectedIndex()
{
	if (this->reportList->count() == 0)
		return -1;
	QModelIndexList index = this->reportList->selectionModel()->selectedIndexes();

	if (index.size() == 0) {
		return -1;
	}
	int indexAtRow = index.at(0).row();
	return indexAtRow;

}

void GUI::northHandler()
{
	this->d.upLat();
	this->populateList();
	this->location_long->setText(QString::fromStdString(d.toString()));

}

void GUI::southHandler()
{
	this->d.lowLat();
	this->populateList();
	this->location_long->setText(QString::fromStdString(d.toString()));

}

void GUI::westHandler()
{
	this->d.upLong();
	this->populateList();

	this->location_long->setText(QString::fromStdString(d.toString()));

}

void GUI::eastHandler()
{
	

	this->d.lowLong();
	this->populateList();
	this->location_long->setText(QString::fromStdString(d.toString()));

}
