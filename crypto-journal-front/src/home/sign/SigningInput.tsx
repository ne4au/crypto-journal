import { FC, useRef, useState, RefObject, useEffect } from 'react';

interface Props  {
    label: string,
    name: string,
    inputReference?: RefObject<HTMLInputElement>,
    value?: string,
    onChange?: () => void,
    type?: string,
    disabled?: boolean,
}
 

const SigningInput: FC<Props> = ({
    label,
    name,
    inputReference,
    value,
    onChange,
    type,
    disabled,
}) => {

    return (
        <div className='flex flex-col text-lg'>
            <input
                type={type}
                id={name}
                ref={inputReference}
                name={name}
                value={value}
                onChange={onChange}
                className={`flex peer px-2 py-1.5 w-full text-gray-200 bg-transparent border-0 border-b-[2px] appearance-none  focus:outline-none 
                focus:ring-0 focus:border-[#57DCE3] ${disabled ? "border-gray-300" : "border-gray-400"}`}
                placeholder=" "
                disabled={disabled}
            />
            <label
                htmlFor={name}
                className="px-2 flex peer-focus:font-medium text-gray-400 duration-300 transform -translate-y-16 scale-75
                 -z-10 origin-[0] peer-focus:text-[#57DCE3] peer-placeholder-shown:scale-100 peer-placeholder-shown:-translate-y-8
                 peer-focus:scale-75 peer-focus:-translate-y-16"
            >

                {label}

            </label>
        </div>
    );
};

export default SigningInput;