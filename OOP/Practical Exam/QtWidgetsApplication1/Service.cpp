#include "Service.h"
#include "Domain.h"
void ReportService::add(string desc, string rep, int lat, int lon, bool val)
{
	/// <summary>
	/// this function creates the report object and adds it to the repo
	///it also validates the object
	/// </summary>
	/// <param name="desc"></param>
	/// <param name="rep"></param>
	/// <param name="lat"></param>
	/// <param name="lon"></param>
	/// <param name="val"></param>
	if (desc == "")
		throw std::exception("Something went wrong!");
	Report r{ desc, rep, lon, lat, val };
	this->repo.addReport(r);
}
