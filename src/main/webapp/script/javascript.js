/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    loadEvents();

});

function dummyData() {
    $('#person-tbody').append(
            "<tr>" +
            "<td class='tabletd'>NaN</td>" +
            "<td class='tabletd'>Patrick</td>" +
            "<td class='tabletd'>Johansen</td>" +
            "<td class='tabletd'>12345678</td>" +
            "<td class='tabletd'><button class='del'>delete</button> / <button class='edit'>edit</button></td>" +
            "</tr>"
            );
    editable();
}

function loadEvents() {

    $.ajax({
        url: 'api/person/all',
        type: 'GET',
        contentType: 'application/json',
        success: function (res) {
            res.forEach(function (entry) {
                $('#person-tbody').append(
                        "<tr>" +
                        "<td class='tabletd'>" + entry.personid + "</td>" +
                        "<td class='tabletd'>" + entry.fname + "</td>" +
                        "<td class='tabletd'>" + entry.lname + "</td>" +
                        "<td class='tabletd'>" + entry.pnumber + "</td>" +
                        "<td><button class='del'>delete</button> / <button class='edit'>edit</button></td>" +
                        "</tr>"
                        );
            });
            editable();
        },
        error: function (res) {
            console.log("Error");
        }
    });

    $("#refresh").click(function () {
        location.reload();
    });

    $("#dummy").click(function () {
        dummyData();
    });

    $("#createperson").click(function () {
        var person = {
            fname: $('#fname').val(),
            lname: $('#lname').val(),
            pnumber: $('#pnumber').val()
        };
        $.ajax({
            url: 'api/person',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(person)
        }).done(function (res) {
            alert(res);
            $('#person-tbody').append(
                        "<tr>" +
                        "<td class='tabletd'>" + res.personid + "</td>" +
                        "<td class='tabletd'>" + res.fname + "</td>" +
                        "<td class='tabletd'>" + res.lname + "</td>" +
                        "<td class='tabletd'>" + res.pnumber + "</td>" +
                        "<td><button class='del'>delete</button> / <button class='edit'>edit</button></td>" +
                        "</tr>"
                        );
                editable();
        });
    });
}

function editable() {
    
    $(".del").click(function () {
        var row = $(this).closest('tr');
        row.remove();
        $.ajax({
            url: 'api/person/' + $(row).find("td:first-child").text(),
            type: 'DELETE'
        }).done(function () {
            alert("We are done");
            $('#person-tbody').remove($(row));
        });
    });

    $(".edit").click(function () {
        var row = $(this).closest('tr');
        var person = {
            personid : $(row).find("td:first-child").text(),
            fname: $("#fname").val(),
            lname: $("#lname").val(),
            pnumber: $("#fname").val()
        };
        $.ajax({
            url: 'api/person',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(person)
        }).done(function(res) {
            $(row).find("td:nth-child(2)").text(res.fname);
            $(row).find("td:nth-child(3)").text(res.lname);
            $(row).find("td:nth-child(4)").text(res.pnumber);
            location.reload();
        });
    });
    
}


