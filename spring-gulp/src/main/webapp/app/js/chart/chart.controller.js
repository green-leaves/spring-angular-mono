angular.module('spring-gulp').controller('ChartController', ChartController);

function ChartController($scope) {
    //$scope.msg = 'Hello World Msg';

    $scope.log = function() {
        //console.log(x(10))
        console.log(width);
        var scale = d3.scaleBand().rangeRound([0, width]);
        scale.domain([100, 999])
        console.log(scale(10));
    }
    var url = 'https://raw.githubusercontent.com/FreeCodeCamp/ProjectReferenceData/master/GDP-data.json';

    var svg = d3.select("svg").attr("width", 1200).attr("height", 500),
        margin = {top: 20, right: 20, bottom: 30, left: 40},
        width = +svg.attr("width") - margin.left - margin.right,
        height = +svg.attr("height") - margin.top - margin.bottom;

    var x = d3.scaleBand().rangeRound([0, width]).padding(10),
        y = d3.scaleLinear().rangeRound([height, 0]);

    var g = svg.append("g")
        .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    d3.json(url, function(payload) {
        var data = payload.data;

        x.domain(data.map(function(d) {
           return d[0];
        }));

        y.domain([0, d3.max(data, function(d) { return d[1] })]);

        g.append("g")
            .attr("class", "axis axis--x")
            .attr("transform", "translate(0," + height + ")")
            .call(
                d3.axisBottom(x)
                .tickFormat(function(d) {
                    return extractYear(d);
                })
                .tickValues(x.domain().filter(filterYear))
            );

        g.append("g")
            .attr("class", "axis axis--y")
            .call(d3.axisLeft(y).ticks(10))
            .append("text")
            .attr("transform", "rotate(-90)")
            .attr("y", 6)
            .attr("dy", "0.71em")
            .attr("text-anchor", "end")
            .text("Gross");

        g.selectAll(".bar")
            .data(payload.data)
            .enter().append("rect")
            .attr("class", "bar")
            .attr("x", function(d) { return x(d[0]); })
            .attr("y", function(d) { return y(d[1]); })
            .attr("width", 5)
            .attr("height", function(d) {
                return height - y(d[1]);
            });
    })


    function extractYear(date) {
        var datePart = date.split("-");
        return datePart[0];
    }

    var years = [];
    function filterYear(d) {
        var year = extractYear(d),
            flag = false;
        if (year % 5 == 0 && years.indexOf(year) == -1) {
            years.push(year);
            flag = true;
        }
        //console.log(years);
        return flag;

    }

}
