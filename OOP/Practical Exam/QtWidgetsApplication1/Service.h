#pragma once
#include "Repo.h"
class DriverService
{
private:
	DriverRepo& repo;
public:
	DriverService(DriverRepo& _r):repo{_r}{}
	void write() { this->repo.write(); }
	std::vector<Driver> getRepo() { return this->repo.getDrivers(); }
};



class ReportService {
private:
	ReportRepo& repo;
public:
	void write() {
		this->repo.write();
	}
	vector<Report> getReports(){ return this->repo.getReports(); }
	ReportService(ReportRepo& _r): repo{_r}{}
	void add(string desc, string rep, int lat, int lon, bool val);
};
