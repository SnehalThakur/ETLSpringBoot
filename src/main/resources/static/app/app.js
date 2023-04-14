function splitLines(sMessage) {
	var retVal = '';
	var lines = sMessage.split('\n');
	for (var line of lines) {
		if (line !== '') {
			retVal += line;
		} else
			break;
		retVal += '<br />';
	}
	return retVal;
}

function execute(){
//    var filePath = document.getElementById("myFile").value;
//loader
    document.getElementById("loader").style.display ="block";

    var filePath = document.getElementById("myFile").files[0].name;
    console.log("filePath: -",filePath);
    var e = document.getElementById("fileType");
    var value = e.value;
    var text = e.options[e.selectedIndex].text;

    var responseFileType = document.getElementById("responseFileType");
    var responseFileTypevalue = responseFileType.value;
    var responseFileTypetext = responseFileType.options[responseFileType.selectedIndex].text;

    console.log(value, text);
    console.log("responseFileTypevalue: ",responseFileTypevalue, responseFileTypetext);

    var query = document.getElementById("query").value;
    console.log("query: -",query);
    let payload={
        "query" : query,
        "inputFileType": value,
        "inputFilePath": filePath,
        "outputFileType": responseFileTypevalue,
        "outputFilePath": ""
    }
    console.log("payload: -",payload);
    var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
             if (this.readyState == 4 && this.status == 200) {
                 console.log(this.responseText);
                 console.log("this.responseText: - ",typeof this.responseText);
                document.getElementById("loader").style.display ="none";

                 document.getElementById("responseTableDiv").style.display ="block";
                 let responseObj = JSON.parse(this.responseText);
                 let templateData='<tr>';
                 let keys = Object.keys(responseObj[0]);
                 keys.forEach(function(item){
                 templateData +='<th>'+item+'</th>'
                 })
                 templateData +='</tr>'
                 console.log("keys: -", keys);


                responseObj.forEach(function(item){
                templateData +='<tr>'
                    const propertyValues = Object.values(item);
                    console.log("propertyValues: -", propertyValues);
                    propertyValues.forEach(function(itemData){
                        templateData +='<td>'+itemData+'</td>'
                    })
                   templateData +='</tr>'
                })
                 document.getElementById("showResponse").innerHTML = templateData;

             }
        };
        xhttp.open("POST", "http://localhost:8099/execute", true);
        xhttp.setRequestHeader("Content-type", "application/json");
        xhttp.send(JSON.stringify(payload));
    }

function exportFileWithType(){
//    var filePath = document.getElementById("myFile").value;
//loader
//    document.getElementById("loader").style.display ="block";
//
//    var filePath = document.getElementById("myFile").files[0].name;
//    console.log("filePath: -",filePath);
console.log("In exp");
    var e = document.getElementById("fileType");
    var value = e.value;
//    var text = e.options[e.selectedIndex].text;
//
    var responseFileType = document.getElementById("responseFileType");
    var responseFileTypevalue = responseFileType.value;
//    var responseFileTypetext = responseFileType.options[responseFileType.selectedIndex].text;
//
//    console.log(value, text);
//    console.log("responseFileTypevalue: ",responseFileTypevalue, responseFileTypetext);

    var query = document.getElementById("query").value;
    console.log("query: -",query);
    let payload={
        "query" : query,
        "inputFileType": value,
        "inputFilePath": "",
        "outputFileType": responseFileTypevalue,
        "outputFilePath": ""
    }
    console.log("payload: -",payload);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
         if (this.readyState == 4 && this.status == 200) {
             console.log(this.responseText);
             console.log("this.responseText: - ",typeof this.responseText);
            document.getElementById("sucess-msg").style.display ="block";
            setTimeout(function(){
            document.getElementById("sucess-msg").style.display ="none";
            },5000)

//             document.getElementById("responseTableDiv").style.display ="block";
//             let responseObj = JSON.parse(this.responseText);
//             let templateData='<tr>';
//             let keys = Object.keys(responseObj[0]);
//             keys.forEach(function(item){
//             templateData +='<th>'+item+'</th>'
//             })
//             templateData +='</tr>'
//             console.log("keys: -", keys);


//            responseObj.forEach(function(item){
//            templateData +='<tr>'
//                const propertyValues = Object.values(item);
//                console.log("propertyValues: -", propertyValues);
//                propertyValues.forEach(function(itemData){
//                    templateData +='<td>'+itemData+'</td>'
//                })
//               templateData +='</tr>'
//            })
//             document.getElementById("showResponse").innerHTML = templateData;

         }
    };
    xhttp.open("POST", "http://localhost:8099/exportfile", true);
    xhttp.setRequestHeader("Content-type", "application/json");
    xhttp.send(JSON.stringify(payload));
}

function noneFunction(type){

        document.getElementById("loader").style.display ="block";
                var filePath = document.getElementById("myFile").files[0].name;
                console.log("filePath: -",filePath);
                var e = document.getElementById("fileType");
                var value = e.value;
                var text = e.options[e.selectedIndex].text;

                var responseFileType = document.getElementById("responseFileType");
                var responseFileTypevalue = responseFileType.value;
                var responseFileTypetext = responseFileType.options[responseFileType.selectedIndex].text;
                let payload={
                        "query" : type == 'display'? "Display none": "Remove none",
                        "inputFileType": value,
                        "inputFilePath": filePath,
                        "outputFileType": responseFileTypevalue,
                        "outputFilePath": ""
                    }
                    console.log("payload: -",payload);
                    var xhttp = new XMLHttpRequest();
                        xhttp.onreadystatechange = function() {
                             if (this.readyState == 4 && this.status == 200) {
                                 console.log(this.responseText);
                                 console.log("this.responseText: - ",typeof this.responseText);
                                document.getElementById("loader").style.display ="none";

                                 document.getElementById("responseTableDiv").style.display ="block";
                                 let responseObj = JSON.parse(this.responseText);
                                 let templateData='<tr>';
                                 let keys = Object.keys(responseObj[0]);
                                 keys.forEach(function(item){
                                 templateData +='<th>'+item+'</th>'
                                 })
                                 templateData +='</tr>'
                                 console.log("keys: -", keys);


                                responseObj.forEach(function(item){
                                templateData +='<tr>'
                                    const propertyValues = Object.values(item);
                                    console.log("propertyValues: -", propertyValues);
                                    propertyValues.forEach(function(itemData){
                                        templateData +='<td>'+itemData+'</td>'
                                    })
                                   templateData +='</tr>'
                                })
                                 document.getElementById("showResponse").innerHTML = templateData;

                             }
                        };
                        xhttp.open("POST", "http://localhost:8099/execute", true);
                        xhttp.setRequestHeader("Content-type", "application/json");
                        xhttp.send(JSON.stringify(payload));

}
    window.addEventListener('load',function(){
    document.getElementById("myFile").addEventListener("change", function () {
    console.log("in side input");
    console.log("this.files: -",this.files);
      if (this.files && this.files[0]) {
      document.getElementById("loader").style.display ="block";
        var myFile = this.files[0];
        var reader = new FileReader();
         var filePath = document.getElementById("myFile").files[0].name;
        console.log("filePath: -",filePath);
        var e = document.getElementById("fileType");
        var value = e.value;
        let payload={
                "query" : "",
                "inputFileType": value,
                "inputFilePath": filePath,
                "outputFileType": value,
                "outputFilePath": ""
            }
            console.log("payload: -",payload);
            var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                     if (this.readyState == 4 && this.status == 200) {
                         console.log(this.responseText);
                         console.log("this.responseText: - ",typeof this.responseText);
                        document.getElementById("loader").style.display ="none";

                         document.getElementById("fileTableDiv").style.display ="block";
                         let responseObj = JSON.parse(this.responseText);
                         let templateData='<tr>';
                         let keys = Object.keys(responseObj[0]);
                         keys.forEach(function(item){
                         templateData +='<th>'+item+'</th>'
                         })
                         templateData +='</tr>'
                         console.log("keys: -", keys);


                        responseObj.forEach(function(item){
                        templateData +='<tr>'
                            const propertyValues = Object.values(item);
                            console.log("propertyValues: -", propertyValues);
                            propertyValues.forEach(function(itemData){
                                templateData +='<td>'+itemData+'</td>'
                            })
                           templateData +='</tr>'
                        })
                         document.getElementById("showFile").innerHTML = templateData;

                     }
                };
                xhttp.open("POST", "http://localhost:8099/execute", true);
                xhttp.setRequestHeader("Content-type", "application/json");
                xhttp.send(JSON.stringify(payload));
//        reader.addEventListener('load', function (e) {
//          document.getElementById("output").textContent = e.target.result;
//        });
//
//        reader.readAsBinaryString(myFile);
      }
    });
    });

//var app = new Vue({
//	el: "#studenti",
//	data: {
//		studenti: null,
//		selektovaniStudent: { mestoRodjenja: { id: 0 } },
//		mesta: null,
//		mod: 0 // 0 browse, 1 edit postojećeg, 2 ubaci novog
//	},
//	mounted() {
//		this.ucitajMesta();
//		this.ucitaj(0, 0);
//	},
//	methods: {
//		select: function (s) {
//			if (this.mod === 0) {
//				this.selektovaniStudent = s;
//			}
//		},
//		snimi: function () {
//			if (this.mod === 2) {
//				this.insert();
//			}
//			else {
//				this.update();
//			}
//		},
//		insert: function () {
//			axios
//				.post('rest/student/insert', this.selektovaniStudent)
//				.then(response => {
//					toast('Student snimljen');
//					this.ucitaj(0, 2);
//				}).catch(
//					error => {
//						console.log('Nije uspelo snimanje.');
//						toast('Nije uspelo snimanje: ' + splitLines(error.response.data.message), true);
//						//this.ucitaj(2, 2);
//					})
//		},
//		update: function () {
//			axios
//				.put('rest/student/update', this.selektovaniStudent)
//				.then(response => {
//					toast('Student izmenjen');
//					this.ucitaj(0, 1);
//				}).catch(
//					error => {
//						console.log('Nije uspelo snimanje.');
//						toast('Nije uspelo snimanje: ' + splitLines(error.response.data.message), true);
//						//this.ucitaj(1, 1);
//					})
//		},
//		obrisi: function (s) {
//			if (confirm('Da li ste sigurni')) {
//				axios
//					.delete('rest/student/delete/' + s.id)
//					.then(response => {
//						this.ucitaj(0, 0);
//					})
//			}
//		},
//		findSelektovaniStudent: function () {
//			if (!this.selektovaniStudent.id)
//				return { mestoRodjenja: this.mesta[0] };
//			for (s of this.studenti) {
//				if (s.id === this.selektovaniStudent.id) {
//					return s;
//				}
//			}
//			return { mestoRodjenja: this.mesta[0] };
//		},
//		ucitaj: function (noviMod, tekuciMod) {
//			axios
//				.get('rest/student/getall')
//				.then(response => {
//					this.studenti = response.data;
//					if (noviMod === 0 && tekuciMod === 0) {
//						this.selektovaniStudent = { mestoRodjenja: this.mesta[0] };
//					} else {
//						this.selektovaniStudent = this.findSelektovaniStudent();
//					}
//					this.mod = noviMod;
//				})
//		},
//		ucitajMesta: function () {
//			axios
//				.get('rest/mesto/getall')
//				.then(response => this.mesta = response.data)
//		},
//		edit: function () {
//			this.mod = 1;
//		},
//		novi: function () {
//			this.selektovaniStudent = { mestoRodjenja: this.mesta[0] };
//			this.mod = 2;
//		},
//		odustani: function () {
//			this.ucitaj(0, this.selektovaniStudent);
//		}
//	}
//});