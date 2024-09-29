/**
 * Cordova Market plugin
 * Author: Cyrus NG
 * License: Apache 2.0
 */
var exec = require('cordova/exec');

var Market = { 

    open: async function (appId) {
        return await this._callNative('open', [appId]);
    },

    /**
     * general error function
     * @return {void}
     */
    onError: function (error) {
        console.log('Market Error: ' + error);
    },

    /**
     * native function bridge
     * @param  {string} name function name in native
     * @param  {*[]} args function params in native
     * @return {string|object} success: return data or string | error: throw { reason, message }
     */
    _callNative: function (name, args) {
        return new Promise((resolve, reject) => exec(data => resolve(data), err => reject(err), 'Market', name, args));
    },

    /**
     * native event bridge
     * @param  {string} name function name in native
     * @param  {function} handler function for processing event
     * @return {void}
     */
    _bindNative: function (name, handler) {
        exec(handler, this.onError, 'Market', name, []);
    }

}

module.exports = Market;
