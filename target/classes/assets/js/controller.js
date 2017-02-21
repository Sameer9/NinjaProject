var controller= angular.module('controller', [
    ]);

controller.controller('controller',["$scope","$window",'Library','Book','$http',function ($scope,$window,Library,Book,$http) {
    function clearModel() {  
       $scope.newbook.name = '';  
       $scope.newbook.author = '';  
       $scope.newbook.price = '';
       $scope.newbook.description = '';  
       $scope.newbook.edit=false;
    } 
    //adding static data
    $scope.edit=false;
    $scope.id=-1;
    $scope.book={name:"",author:"",price:"",description:"",edit:false};
    
    $scope.DeleteData = function (book) {
    	console.log(book);
    	console.log("I'm Here");
        Library.removeBook(book)
        .then(function(data){
        	Library.getAllBooks()
        	.then(function(data){
        		$scope.books=data.data;
        	});
        	
        });
    } 

    $scope.addData = function (book) { 
        if(!book.name){
            $window.alert("Name cannot contain special characters");
        }
        if( !book.author || !book.price || !book.description){
            $window.alert("All details are required");
            return ;
        }
        for(i in $scope.books){
            if(i == book.name){
                $window.alert("Book already exists");
                return ;
            }
        }
        var b={name:book.name,author:book.author,price:book.price,description:book.description};
        Library.addBook(b)
        .then(function(data){
        	Library.getAllBooks()
        	.then(function(data){
        		$scope.books=data.data;
        	});
        });
        $scope.newbook={};
    } 
    $scope.editData = function(book){
//        $scope.id = Library.books.indexOf(book);  
        book.edit=true;
        $scope.editbook=book;     
    }

    $scope.update = function(book){
    	console.log(book);
    	delete book['edit'];
        $http.post("/EditBooks",book).success(function(data){
        		Library.getAllBooks().then(function(data){
        		$scope.books=data.data;
        	});
        });
//        book.edit=false;
        // $scope.editbook={};

    }
    
    $http.get('/PrintingAllBooks').success(function(data){
    	$scope.books=data;
    });
//    $scope.books=Library.getAllBooks();    
}]);