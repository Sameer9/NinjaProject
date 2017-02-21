var Library=angular.module('Library',[]);

Library.factory('Library',['$http',function($http){
	var Library={};
	Library.books=[];
	Library.books.push({name:"book1",author:"author1",price:"300",description:"abc"});
    Library.books.push({name:"book2",author:"author2",price:"500",description:"def"});
	Library.addBook=function (book)
	{
		return $http.post("/AddBooks",book).success(function(data){});
	}
	
	Library.getAllBooks = function()
	{
		return $http.get("/PrintingAllBooks").success(function(data) {
			Library.books=data;
		});
	}
	
	Library.removeBook=function(book)
	{
		return $http.post("/DeleteBooks",book).success(function(data){var _index = Library.books.indexOf(book);  
        Library.books.splice(_index, 1);}).error(function(data){console.log(data)});
	}
	return Library;
}]);