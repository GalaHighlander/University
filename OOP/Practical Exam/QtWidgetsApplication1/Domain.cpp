#include "Domain.h"
#include <string>
#include <vector>
#include <sstream>
#include <fstream>
#include <iostream>
using namespace std;

vector<string> tokenize(string input)
{
	vector<string> tokens;
	stringstream spliter(input);
	string token;
	while (getline(spliter, token, ','))
	{
		tokens.push_back(token);
	}
	return tokens;
}

Driver::Driver(string _name, string _status, int longitude, int latitude, int score)
{
	name = _name;
	status = _status;
	this->location.first = longitude;
	this->location.second = latitude;
	this->score = score;
}

std::string Driver::toString() const
{
	stringstream buffer;
	buffer << this->name << "," << this->status << "," << this->location.first << "," << this->location.second << "," << this->score << std::endl;
	return buffer.str();
}

istream& operator>>(istream& input_stream, Driver& d)
{
	string get_input;
	getline(input_stream, get_input);
	vector<string> tokens = tokenize(get_input);
	if (tokens.size() != 0)
	{
		d.name = tokens[0];
		d.status = tokens[1];
		d.location.first = stoi(tokens[2]);
		d.location.second = stoi(tokens[3]);
		d.score = stoi(tokens[4]);
	}
	return input_stream;
}

ostream& operator<<(ostream& output_stream, Driver& d)
{
	output_stream << d.toString();
	return output_stream;
}

istream& operator>>(istream& input_stream, Report& d)
{
	string get_input;
	getline(input_stream, get_input);
	vector<string> tokens = tokenize(get_input);
	if (tokens.size() != 0)
	{
		d.description = tokens[0];
		d.reporter = tokens[1];
		//d.status = tokens[1];
		d.location.first = stoi(tokens[2]);
		d.location.second = stoi(tokens[3]);
		//d.score = stoi(tokens[4]);
		if (tokens[4] == "true")
			d.validated = true;
		else
			d.validated = false;
	}
	return input_stream;
}

ostream& operator<<(ostream& output_stream, Report& d)
{
	output_stream << d.toString();
	return output_stream;
}

Report::Report(string desc, string rep, int lat, int lon, bool val)
{
	description = desc;
	reporter = rep;
	location.first = lon;
	location.second = lat;
	validated = val;
}

std::string Report::toString() const
{
	stringstream buffer;
	buffer << description << "," << reporter << "," << location.first << "," << location.second << ",";
	if (validated)
		buffer << "true" << std::endl;
	else
		buffer << "false" << std::endl;
	return buffer.str();
}
