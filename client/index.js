const fetch = require('isomorphic-unfetch');
const querystring = require('querystring');

class TrinityFood {
    constructor() {
      this.basePath = 'http://35.195.182.8:8081/task-api/';
    }

    getOutlets(options) {
        // Alternative possible fix location
        const queryString = options ? '?' + querystring.stringify(options) : '';

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
            method: 'GET'
        };

        return this.request(url, config);
    }

    getFoodsByOutlet(outletId, options) {
        const queryString = options ? '?' + querystring.stringify(options) : '';

        // Url string can be simplified -- but how( ? :o) )
        const url = `foods/v1/${outletId}${queryString ? '/' : ''}${queryString}`;

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

const foodApi = new TrinityFood();

// Options object being passed in is breaking this
foodApi.getOutlets({}).then(data => console.log(data));
// TODO: break here
foodApi.getFoods().then(data => console.log(data));
// TODO: break here
foodApi.getFoodsByOutlet(1).then(data => console.log(data));
