#include "Repo.h"

void DriverRepo::read()
{
	Driver d{};
	ifstream file("drivers.txt");
	while (file >> d)
		this->drivers.push_back(d);
	file.close();
}

void DriverRepo::write()
{
	ofstream file("drivers.txt");
	if (!file.is_open())
		throw exception("file failed");
	for (auto& item : drivers)
		file << item;
	file.close();
}

void ReportRepo::read()
{
	Report d{};
	ifstream file("reports.txt");
	while (file >> d)
		this->reports.push_back(d);
	file.close();
}

void ReportRepo::write()
{
	ofstream file("reports.txt");
	if (!file.is_open())
		throw exception("file failed");
	for (auto& item : reports)
		file << item;
	file.close();
}

void ReportRepo::addReport(Report r)
{
	
		reports.push_back(r); 
		this->write();
		///this function takes the created report object and adds it to the REPO
		///it also writes it to the file
	
}
