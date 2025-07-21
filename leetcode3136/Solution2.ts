function isValid(word: string): boolean {
    if(word.length < 3) return false
    const REGEX = /[0-9a-zA-Z]/g
    const ALPH_REGEX = /[a-zA-Z]/
    if (word.match(REGEX).join('') !== word){
        return false
    }
    const vowels = [ 'a', 'e', 'i', 'o', 'u']
    let hasVowel = false
    let hasConsonant = false
    for(const char of word) {
        if(ALPH_REGEX.test(char) && !vowels.includes(char.toLowerCase())) {
            hasConsonant = true
        }
        if(vowels.includes(char.toLowerCase())) {
            hasVowel = true
        }
        if(hasVowel && hasConsonant) {
            return true
        }
    }
    return false
};
