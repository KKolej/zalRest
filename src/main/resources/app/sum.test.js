const sum = require('./sum');

test('add 1 + 2should be 3', () => {
    expect(sum(1,2)).toBe(3)
})