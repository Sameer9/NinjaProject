var Book=angular.module('Book',[]);

Book.factory('Book',function()
{
	var Book={};
	function Book(name,author,price,description)
	{
		Book.name=name;
		Book.author=author;
		Book.price=price;
		Book.description=description;
	}
	
	Book.addBook= function(name,author,price,description)
	{
		this.name=name;
		this.author=author;
		this.description=description;
		this.price=price;
	}
	return Book;
});