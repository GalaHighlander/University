#include <iostream>
#include <vector>
#include <string>
#include <sstream>
#include <fstream>
#include <assert.h>
using namespace std;
class Handrail {
public:
	Handrail();
	virtual double computePrice();
	virtual ~Handrail();
};
class WoodHandrail : public Handrail {
public:
	WoodHandrail() = default;
	double computePrice() override { return 2; }
	~WoodHandrail() = default;
};
class MetalHandrail : public Handrail {
public:
	MetalHandrail() = default;
	double computePrice() override { return 2.5; }
	~MetalHandrail() = default;
};
class HandrailWithVerticalElements : public Handrail {
private:
	Handrail* h;
	int noElements;
public:
	HandrailWithVerticalElements(Handrail* _h, int nr) : h{ _h }, noElements{ nr }{};
	~HandrailWithVerticalElements() = default;
	double computePrice() override { return (h->computePrice() + (5 * noElements)); }
};
class Stair {
private:
	int noSteps;
public:
	Stair(int _noSteps) { noSteps = _noSteps; };
	virtual ~Stair();
	virtual double getPrice() { return noSteps * getCoefficient(); }
	virtual std::string  getDescription();
	virtual double getCoefficient();
};
class WoodStair : public Stair {
public:
	WoodStair(int noSteps) : Stair{ noSteps } {}
	~WoodStair() = default;
	std::string getDescription() override { return "wood stair"; }
	double getCoefficient() override { return 1.5; }
};
class MetalStair : public Stair {
public:
	MetalStair(int noSteps) : Stair{ noSteps } {}
	~MetalStair() = default;
	std::string getDescription() override { return "metal stair"; }
	double getCoefficient() override { return 2; }
};
class StairWithHandrail : public Stair {
private:
	double handrailLinearMeters;
	Handrail* h;
	Stair* s;
public:
	StairWithHandrail(int noSteps, int haha, Stair* _s, Handrail* _h) : Stair{ noSteps }, handrailLinearMeters{ haha }, s{ _s }, h{ _h } {}
	double getPrice() override { return s->getPrice() + (h->computePrice() * handrailLinearMeters); }
	double getCoefficient() override { return 1; }
	std::string getDescription() override;
};
int main() {
	Stair* w_stair = new WoodStair{ 20 };
	Stair* m_stair = new MetalStair{ 10 };
	Handrail* m_handrail = new MetalHandrail{};
	Stair* s_w_h = new StairWithHandrail{ 10, 5, m_stair, m_handrail };
	Stair* w_stair2 = new WoodStair{ 10 };
	Handrail* w_handrail = new WoodHandrail{};
	Handrail* hwve = new HandrailWithVerticalElements{ w_handrail, 10 };
	Stair* fancyWood = new StairWithHandrail{ 10,5, w_stair2, hwve };
	cout << w_stair->getDescription();
	cout << m_stair->getDescription();
	cout << s_w_h->getDescription();
	cout << fancyWood->getDescription();
	/// <summary>
	/// /////////////////
	/// </summary>
	/// <returns></returns>
	cout << w_stair->getPrice();
	cout << m_stair->getPrice();
	cout << s_w_h->getPrice();
	cout << fancyWood->getPrice();
	delete w_stair; delete m_stair; delete s_w_h; delete fancyWood; delete w_handrail; delete w_stair2;
	return 0;
}
std::string StairWithHandrail::getDescription()
{
	std::stringstream buffer;
	buffer << s->getDescription() << " " << handrailLinearMeters;
	return buffer.str();
}