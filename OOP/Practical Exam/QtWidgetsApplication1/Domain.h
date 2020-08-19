#pragma once
#include <string>
#include <sstream>
#include <fstream>
#include <iostream>

using namespace std;
class Driver {
private:
	string name, status;
	pair<int, int> location;
	int score;
public:
	Driver() = default;
	Driver(string _name, string _status, int longitude, int latitude, int score);
	~Driver() = default;
	void setName(string _n) { name = _n; }
	void setStatus(string _s) { status = _s; }
	void setLocation(int s1, int s2) { this->location.first = s1; this->location.second = s2; }
	string getName() { return name; }
	string getStatus() { return status; }
	void setScore() { this->score++; }
	pair<int, int> getLocation() { return location; }
	int getScore() { return score; }
	void upLat() { this->location.second++; }
	void lowLat() { this->location.second--; }
	void upLong() { this->location.first++; }
	void lowLong() { this->location.first--; }
	friend istream& operator>>(istream& input_stream, Driver& d);
	friend ostream& operator<<(ostream& output_stream, Driver& d);
	std::string toString() const;


};

class Report {
private:
	string description, reporter;
	pair<int, int> location;
	bool validated;
public:
	Report() = default;
	Report(string desc, string rep, int lat, int lon, bool val);
	friend istream& operator>>(istream& input_stream, Report& d);
	std::string getRep() { return reporter; }
	friend ostream& operator<<(ostream& output_stream, Report& d);
	void setTrue() { this->validated = true; }
	std::string toString() const;
	pair<int, int> getLocation() { return location; }

};