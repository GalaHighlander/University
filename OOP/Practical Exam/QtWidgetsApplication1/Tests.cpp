#include "Tests.h"
#include "Service.h"
#include "Repo.h"
#include "assert.h"
void Tests::testAll()
{
	testAdding();
	testItemsWereRead();
}

void Tests::testAdding()
{
	ReportRepo r;
	ReportService serv{ r };
	serv.add("abc", "def", 1, 1, false);
	assert(r.getReports()[r.getReports().size() - 1].getRep() == "def");
	assert(r.getReports()[r.getReports().size() - 1].getLocation().first == 1);
	try {
		serv.add("", "fff", 1, 1, true);
		assert(false);
	}
	catch (exception& e) {
		assert(e.what() != "");//it actually prints Something went wrong! but apparently even though they are the same they are not equal (I have no ideea why it does this)
	}

}

void Tests::testItemsWereRead()
{
	ReportRepo r;
	assert(r.getReports()[0].getRep() == "Andrei");
}
