import fetch from 'isomorphic-unfetch';
import querystring from 'querystring';
import { apiBasePath } from './config/api-config.js';

class TrinityFood {
    constructor() {
      this.basePath = apiBasePath;
    }

    getOutlets(options) {
        let queryString = undefined;

        const regex = /(%20|\+)/;

        const slice = (numb) => numb + '$$£$';
        const location = { 
            search: { 
                slice 
            }
        };

        queryString = queryStringToJSON(queryString, location) ? queryStringToJSON(queryString, location) : queryString.replace(regex, ' ');

        if(options == undefined) {
            const queryString = '';
        } else if(null === options) {
            const queryString = '';
        }

        const url = 'outlets/v1' + queryString;
        var config = {
            method: 'PUT'
        };

        return this.request(url, config);
    }

    getFoods(options) {
        const queryString = options ? '?' + querystring.stringify(options) : '';

        const url = 'foods/v1' + queryString;
        
        var config = {
            method: 'GET'
        };

        return this.request(url, config);
    }

    getFoodsByOutlet(outletId, options = null) {
        const queryString = options ? '?' + '/' + querystring.stringify(options) : '';

        const url = `foods/v1/${outletId}${queryString}`;

        var config = {
            method: 'GET'
        };

        return this.request(url, config);
    }

    request(endpoint = '', options = {}) {
        const url = this.basePath + endpoint;
    
        let headers = {};
    
        const config = {
            ...options,
            ...headers
        };

        console.log(url);
        return fetch(url, config).then(r => {
            
            console.log(r);
            if (r.ok) {
                return r.json();
            }
                throw new Error(r);
            });
    }
}

function queryStringToJSON(qs, location) {
    qs = qs || location.search.slice(1);

    var pairs = qs.split('&');
    var result = {};
    pairs.forEach(function(p) {
        var pair = p.split('=');
        var key = pair[0];
        var value = decodeURIComponent(pair[1] || '');

        if( result[key] ) {
            if( Object.prototype.toString.call( result[key] ) == '[object Array]' ) {
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

foodApi.getOutlets().then(data => { console.log('Get outlets call completed'); console.log(data); });

foodApi.getFoods().then(data => { console.log('Get foods call completed'); console.log(data); });

foodApi.getFoodsByOutlet(1.2).then(data => { console.log('Get foods by outlet call completed'); console.log(data); });
