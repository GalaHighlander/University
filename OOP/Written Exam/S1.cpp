#include <iostream>
#include <vector>
#include <string>
#include <sstream>
#include <fstream>
#include <assert.h>
using namespace std;
template <typename T>
class Adder {
private:
	std::vector<T> numbers;
	int suma;
public:
	Adder(T number) { numbers.push_back(number); suma = 0; }
	~Adder() = default;
	void operator+(T number);
	void operator++() { numbers.push_back(numbers[numbers.size() - 1]); }
	void operator--();
	int sum();
};
void function2() {
	Adder<int> add{ 5 }; // build a new Adder, with initial value 5
	add = add + 5 + 2;	// add values 5 and 2
	++add;	// adds the last added value (2) again
	add + 8;	// add value 8
	cout << add.sum() << "\n"; // print sum, so far: 22 (5+5+2+2+8)
	--add; // eliminate last added value
	cout << add.sum() << "\n"; // print modified sum: 14 (5+5+2+2)
	--(--add); // eliminate the two previously added values
	cout << add.sum() << "\n"; // print modified sum: 10
	try {
		--(--(--add));
	}
	catch (runtime_error& ex) {
		cout << ex.what(); // prints "No more values!";
	}
}
int main() {
	function2();
	return 0;
}
template<typename T>
void Adder<T>::operator+(T number)
{
	this->numbers.push_back(number);
}
template<typename T>
void Adder<T>::operator--()
{
	if (numbers.size() != 0)
		numbers.pop_back();
	else
		throw runtime_error("No more values!");
}
template<typename T>
int Adder<T>::sum()
{
	suma = 0;
	for (auto& nr : numbers)
		suma += nr;
	return suma;
}
