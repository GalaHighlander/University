#pragma once
#include "Domain.h"
#include <vector>
using namespace std;
class DriverRepo
{
private:
	vector<Driver> drivers;
public:
	DriverRepo() { read(); }
	void addDriver(Driver d) { drivers.push_back(d); }
	vector<Driver> getDrivers() { return drivers; }
	void read();
	void write();
};

class ReportRepo {
private:
	vector<Report> reports;
public:
	ReportRepo() { read(); }
	void read();
	void write();
	void addReport(Report r);
	vector<Report> getReports() { return reports; }
};
