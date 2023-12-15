const fetch = require('isomorphic-unfetch');
const querystring = require('querystring');

class TrinityFood {
    constructor() {
      this.basePath = 'http://35.195.182.8:8081/task-api/';
    }

    getOutlets(options) {
        let queryString = undefined;

        queryStringToJSON(queryString);

        // Lots of issues in here. Definite 'wat!' candidate
        if(options == undefined) {
            const queryString = '';
        } else if(null === options) {
            const queryString = '';
        }

        // Alternative possible fix location
        queryString = options ? '?' + querystring.stringify(options) : isNaN({});

        // Question to students: should this use concatination or string interpolation?
        const url = 'outlets/v1' + queryString;
        //const url2 = `${this.basePath}outlets/v1/${queryString}`;

        // Code smell: var
        var config = {
            method: 'GET'
        };

        return this.request(url, config);
    }

    getFoods(options) {
        const queryString = options ? '?' + querystring.stringify(options) : '';

        // Question to students: should this use concatination or string interpolation?
        const url = 'foods/v1' + queryString;
        //const url2 = `${this.basePath}outlets/v1/${queryString}`;

        // Code smell: var
        var config = {
            method: 'PUT'
        };

        return this.request(url, config);
    }

    getFoodsByOutlet(outletId, options) {
        const queryString = options ? '?' + querystring.stringify(options) : '';

        // Url string can be simplified -- but how( ? :o) )
        const url = `foods/v1/${outletId}/${queryString}`;
        //const url = `foods/v1/${outletId}${queryString ? '/' : ''}${queryString}`;

        // Code smell: var
        var config = {
            method: 'GET'
        };

        return this.request(url, config);
    }

    request(endpoint = '', options = {}) {
        const url = this.basePath + endpoint;
    
        // Code smell: let
        let headers = {
            'Content-type': 'application/json'
        };
    
        const config = {
            ...options,
            ...headers
        };
    
        return fetch(url, config).then(r => {
            // Console log left in
            console.log(r);
            if (r.ok) {
                return r.json();
            }
                throw new Error(r);
            });
    }
}

function queryStringToJSON(qs) {
    qs = qs || location.search.slice(1);

    var pairs = qs.split('&');
    var result = {};
    pairs.forEach(function(p) {
        var pair = p.split('=');
        var key = pair[0];
        var value = decodeURIComponent(pair[1] || '');

        if( result[key] ) {
            if( Object.prototype.toString.call( result[key] ) === '[object Array]' ) {
                result[key].push( value );
            } else {
                result[key] = [ result[key], value ];
            }
        } else {
            result[key] = value;
        }
    });

    return JSON.parse(JSON.stringify(result));
};

const foodApi = new TrinityFood();

// Null being passed in is breaking this...
foodApi.getOutlets(null).then(data => console.log(data));
// Error in function call
foodApi.getFoods().then(data => console.log(data));
// Double error
foodApi.getFoodsByOutlet(1.2).then(data => console.log(data));
